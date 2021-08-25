package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.services.GachaPool.GachaPoolService;
import net.eriagacha.services.GachaPool.GachaPoolServiceFactory;
import net.eriagacha.utils.NetworkHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.LiteralText;

@Log4j2
public class NetworkServer {

  public static void initializeNetwork() {
    log.info("Loading server side network");
    serverSide();
  }

  public static void serverSide() {
    ServerPlayNetworking.registerGlobalReceiver(NetworkHelper.ID_TEST_SEND,
        (server, player, handler, buf, responseSender) -> {
          int clientMoneyConditionRawId = buf.readInt();
          int clientMoneyQuantity = buf.readInt();
          server.execute(() -> {
            log.info("Server recived with ID : {}", NetworkHelper.ID_TEST_SEND);
            log.info("Data: {} - {} - {} - {}", player, handler, buf, responseSender);
            try {
              GachaPoolService gachaPoolService =
                  GachaPoolServiceFactory.getInstance(clientMoneyConditionRawId);
              if (gachaPoolService.conditionsMet()) {
                gachaPoolService.getReward(player, clientMoneyConditionRawId, clientMoneyQuantity);
              } else {
                player.sendMessage(new LiteralText("Conditions to use the gacha not met"), false);
              }
            } catch (Exception e) {
              log.error(String.format("Exception in serverSide with ID %s - Message : %s",
                  NetworkHelper.ID_TEST_SEND, e.getMessage()));
            }
          });
        });
  }
}
