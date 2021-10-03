package com.eriagacha.network;

import com.eriagacha.gacha.GachaPoolService;
import com.eriagacha.gacha.GachaPoolServiceFactory;
import com.eriagacha.utils.NameSpaces;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Log4j2
public class NetworkServer {


  private NetworkServer() {
  }

  public static void initializeNetwork() {
    log.info("Loading server side network");
    serverSide();
  }


  public static void serverToClientDrawParticule(World world, BlockPos pos) {
    try {
      PacketByteBuf message = PacketByteBufs.create();
      message.writeBlockPos(pos);
      for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, pos)) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, NameSpaces.Network.ID_C2S_SEND_PARTICLE, message);
      }
    } catch (Exception e) {
      log.fatal("Client exception in serverToClientDrawParticule() with error {}", e.getMessage());
    }
  }

  public static void serverSide() {
    ServerPlayNetworking.registerGlobalReceiver(NameSpaces.Network.ID_C2S_SEND_GACHA,
        (server, player, handler, buf, responseSender) -> {
          int clientMoneyConditionRawId = buf.readInt();
          int clientMoneyQuantity = buf.readInt();
          ItemStack itemStack =
              new ItemStack(Item.byRawId(clientMoneyConditionRawId), clientMoneyQuantity);
          server.execute(() -> {
            try {
              GachaPoolService gachaPoolService =
                  GachaPoolServiceFactory.getInstance(clientMoneyConditionRawId);
              assert gachaPoolService != null : "NullPointerException";
              gachaPoolService.rollGacha(player, itemStack);
            } catch (Exception e) {
              log.error("Exception in serverSide with ID {} - Message : {}",
                  NameSpaces.Network.ID_C2S_SEND_GACHA, e.getMessage());
            }
          });
        });
  }
}
