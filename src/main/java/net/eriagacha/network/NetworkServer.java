package net.eriagacha.network;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.gacha.GachaPoolService;
import net.eriagacha.gacha.GachaPoolServiceFactory;
import net.eriagacha.utils.NameSpaces;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Log4j2
public class NetworkServer {


  private NetworkServer() {
  }

  public static void initializeNetwork() {
    log.info("Loading server side network");
    serverSide();
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
