package com.eriagacha;

import com.eriagacha.event.EventHandler;
import com.eriagacha.network.NetworkClient;
import com.eriagacha.network.NetworkServer;
import com.eriagacha.register.RegisterCommands;
import com.eriagacha.register.RegisterItems;
import lombok.extern.log4j.Log4j2;
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