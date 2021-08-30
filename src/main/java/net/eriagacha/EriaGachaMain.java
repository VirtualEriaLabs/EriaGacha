package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.event.EventHandler;
import net.eriagacha.network.NetworkClient;
import net.eriagacha.network.NetworkServer;
import net.eriagacha.register.RegisterCommands;
import net.eriagacha.register.RegisterItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

@Log4j2
public class EriaGachaMain implements ModInitializer {

  @Override
  public void onInitialize() {
    EventHandler.init();
    ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
      try {
        NetworkServer.initializeNetwork();
      } catch (Exception e) {
        log.error("Exception onInitialize at EriaGachaMain - Message : {}",
            e.getMessage());
      }
    });
    if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
      NetworkClient.init();
    }
    RegisterCommands.init();
    RegisterItems.init();
  }
}