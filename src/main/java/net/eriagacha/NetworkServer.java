package net.eriagacha;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaController;
import net.eriagacha.utils.GachaUtils;
import net.eriagacha.utils.NetworkHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

@Log4j2
@Environment(EnvType.SERVER)
public class NetworkServer {

  public static void testNetwork() {
    log.fatal("Testing networking");
    serverSide();
  }

  public static void serverSide() {
    ServerPlayNetworking.registerGlobalReceiver(NetworkHelper.IDEF_TEST_SEND,
        (server, player, handler, buf, responseSender) -> {
          server.execute(() -> {
            log.error("Me ha llegado el paquete de cliente.");
            log.error(
                String.format("Data: %s - %s - %s - %s", player, handler, buf, responseSender));
            try {
              GachaController.giveGachaReward(player, GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT);
              responseSender.sendPacket(NetworkHelper.IDEF_TEST_RESPONSE, buf);
            } catch (CommandSyntaxException e) {
              log.error(
                  String.format("Exception in serverSide with IDEF %s - Message : %s",
                      NetworkHelper.IDEF_TEST_SEND,
                      e.getMessage()));
            }
          });
        });

    ServerPlayerEntity.getOfflinePlayerUuid("SerexG");

  }
}
