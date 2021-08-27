package net.eriagacha.network;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.utils.NetworkHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

@Log4j2
@Environment(EnvType.CLIENT)
public class NetworkClient {
  private NetworkClient() {
  }

  public static void gachaSend(ItemStack moneyCondition) {
    int moneyConditionId = Item.getRawId(moneyCondition.getItem());
    int itemQuantity = moneyCondition.getCount();
    PacketByteBuf message = PacketByteBufs.create();
    message.writeInt(moneyConditionId);
    message.writeInt(itemQuantity);
    try {
      ClientPlayNetworking.send(NetworkHelper.ID_TEST_SEND, message);
    } catch (Exception e) {
      log.fatal("Client exception in gachaSend() with error {}", e.getMessage());
    }

  }

  public static void init() {
    ClientPlayNetworking.registerGlobalReceiver(NetworkHelper.ID_TEST_RESPONSE,
        (player, handler, buf, packetSender) -> {
          log.error("Client Recived packet with ID {}", NetworkHelper.ID_TEST_RESPONSE);
          log.error("Data: {} - {} - {} - {}", player, handler, buf, packetSender);
          try {
            var bufInt = buf.readInt();
            var x = buf.readString();
            log.fatal("Data : {}", x);
          } catch (Exception e) {
            log.error("Exception in client side with ID {} - Message : {}",
                NetworkHelper.ID_TEST_RESPONSE, e.getMessage());
          }
        });
  }

}
