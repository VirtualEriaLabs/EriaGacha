package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.event.EventHandler;
import net.eriagacha.event.GachaRollTelemetryObserver;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
@SpringBootApplication
public class EriaGachaServerMain implements DedicatedServerModInitializer {

  public static final ConfigurableApplicationContext springContext =
      SpringApplication.run(EriaGachaServerMain.class);

  @Override
  public void onInitializeServer() {
    EventHandler.gre.addObserver(new GachaRollTelemetryObserver());

    ServerLifecycleEvents.SERVER_STOPPED.register(minecraftServer -> {
      try {
        SpringApplication.exit(springContext);
      } catch (Exception e) {
        log.error("Exception onInitialize at EriaGachaServerMain.onInitializeServer - Message : {}",
            e.getMessage());
      }
    });
  }
}