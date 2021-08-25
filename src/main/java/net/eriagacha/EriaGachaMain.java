package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
@SpringBootApplication
public class EriaGachaMain implements ModInitializer {

  public static ConfigurableApplicationContext springContext;


  @Override
  public void onInitialize() {

    ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
      try {
        initializeServerSide();
      } catch (Exception e) {
        log.error(
            String.format("Exception onInitialize at testNetwork() - Message : %s",
                e.getMessage()));
      }
      try {
        NetworkServer.initializeNetwork();
      } catch (Exception e) {
        log.error(
            String.format("Exception onInitialize at testNetwork() - Message : %s",
                e.getMessage()));
      }
    });
    if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
      NetworkClient.init();
    }

    RegisterCommands.init();
    RegisterItems.init();
  }

  public void initializeServerSide() {
    springContext = SpringApplication.run(EriaGachaMain.class);
  }
}