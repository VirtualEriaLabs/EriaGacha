package com.eriagacha.network;

import com.eriagacha.utils.NameSpaces;
import com.eriagacha.utils.ParticleUtils;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

@Log4j2
@Environment(EnvType.CLIENT)
public class NetworkClient {
  private NetworkClient() {
  }

  public static void clientToServerGachaRoll(ItemStack moneyCondition) {
    int moneyConditionId = Item.getRawId(moneyCondition.getItem());
    int itemQuantity = moneyCondition.getCount();
    PacketByteBuf message = PacketByteBufs.create();
    message.writeInt(moneyConditionId);
    message.writeInt(itemQuantity);
    try {
      ClientPlayNetworking.send(NameSpaces.Network.ID_C2S_SEND_GACHA, message);
    } catch (Exception e) {
      log.fatal("Client exception in gachaSend() with error {}", e.getMessage());
    }
  }


  public static void init() {
    ClientPlayNetworking.registerGlobalReceiver(NameSpaces.Network.ID_S2C_RESPONSE_GACHA,
        (player, handler, buf, packetSender) -> {});

    ClientPlayNetworking.registerGlobalReceiver(NameSpaces.Network.ID_C2S_SEND_PARTICLE,
        (player, handler, buf, packetSender) -> {
        BlockPos pos = buf.readBlockPos();
        ParticleUtils.soulFlameArea(pos,player);
        });
  }
}
