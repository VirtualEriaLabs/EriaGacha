package net.eriagacha;

import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.utils.NetworkHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;

@Log4j2
@Environment(EnvType.CLIENT)
public class NetworkClient {
  public static void clientSide() {
    ClientPlayNetworking.send(NetworkHelper.IDEF_TEST_SEND,
        new PacketByteBuf(new EmptyByteBuf(new UnpooledByteBufAllocator(true))));


    ClientPlayNetworking.registerGlobalReceiver(NetworkHelper.IDEF_TEST_RESPONSE,
        (player, handler, buf, packetSender) -> {
          log.error(
              String.format("Client Recived packet with IDEF %s",
                  NetworkHelper.IDEF_TEST_RESPONSE));

          log.error(String.format("Data: %s - %s - %s - %s", player, handler, buf, packetSender));
          try {
            player.getName();
          } catch (Exception e) {
            log.error(
                String.format("Exception in serverSide with IDEF %s - Message : %s",
                    NetworkHelper.IDEF_TEST_RESPONSE,
                    e.getMessage()));
          }
        });
  }
}
