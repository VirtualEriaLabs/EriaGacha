package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
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

    boolean networking = true;
    if (networking && FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
      try {
        initializeServerSide();
      } catch (Exception e) {
        log.fatal(
            String.format("Exception onInitialize at testNetwork() - Message : %s",
                e.getMessage()));
      }
      try {
        NetworkServer.testNetwork();
      } catch (Exception e) {
        log.fatal(
            String.format("Exception onInitialize at testNetwork() - Message : %s",
                e.getMessage()));
      }
    }

    RegisterCommands.init();
    RegisterItems.init();

  }

  public void initializeServerSide() {
    springContext = SpringApplication.run(EriaGachaMain.class);
  }







}