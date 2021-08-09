package net.eriagacha;

import net.fabricmc.api.ModInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EriaGachaMain implements ModInitializer {

	public static ConfigurableApplicationContext springContext;
		@Override
		public void onInitialize() {
			springContext = SpringApplication.run(EriaGachaMain.class, new String[] {});
			RegisterCommands.init();
			RegisterItems.init();
	}
}