package net.eriagacha;

import java.lang.reflect.InvocationTargetException;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
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

    RegisterCommands.init();
    RegisterItems.init();
    try {
      var x = getClass().getMethod("initializeServerSide");
      x.invoke(new EriaGachaMain());
      initializeServerSide();
    } catch (NoSuchMethodException e) {
      log.info(e.getMessage());
    } catch (InvocationTargetException e) {
      log.info(e.getMessage());
    } catch (IllegalAccessException e) {
      log.info(e.getMessage());
    }
  }
  @Environment(EnvType.SERVER)
  public void initializeServerSide(){
    springContext = SpringApplication.run(EriaGachaMain.class);
  }
}