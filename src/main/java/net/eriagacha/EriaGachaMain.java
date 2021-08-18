package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
@SpringBootApplication
public class EriaGachaMain implements ModInitializer {

  public static ConfigurableApplicationContext springContext;
  @Override
  public void onInitialize() {
    springContext = SpringApplication.run(EriaGachaMain.class);
    RegisterCommands.init();
    RegisterItems.init();
  }
}