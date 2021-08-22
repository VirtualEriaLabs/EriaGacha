package net.eriagacha;

import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
@SpringBootApplication
public class EriaGachaMain implements ModInitializer {

  @Environment(EnvType.SERVER)
  public static ConfigurableApplicationContext springContext;

  @Override
  public void onInitialize() {

    boolean networking = true;
    if (networking) {
      testNetwork();
    }

    RegisterCommands.init();
    RegisterItems.init();

    if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
      log.info("Soy un cliente muy bonito");
    } else {
      initializeServerSide();
    }
  }

  @Environment(EnvType.SERVER)
  public void initializeServerSide() {
    springContext = SpringApplication.run(EriaGachaMain.class);
  }


  public void testNetwork() {
    log.fatal("Testing networking");
    serverSide();
  }

  public void serverSide() {
    ServerPlayNetworking.registerGlobalReceiver(new Identifier("testo"),
        (server, player, handler, buf, responseSender) -> {
          server.execute(() -> {
            log.error("Me ha llegado el paquete de cliente.");
            log.error(String.format("Data: %s - %s - %s - %s", player, handler, buf, responseSender));
          });
        });

    ServerPlayerEntity.getOfflinePlayerUuid("SerexG");

  }

  public static void clientSide() {
    ClientPlayNetworking.send(new Identifier("testo"),
        new PacketByteBuf(new EmptyByteBuf(new UnpooledByteBufAllocator(true))));
  }


}