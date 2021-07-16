package net.eriagacha;

import net.fabricmc.api.ModInitializer;


public class EriaGachaMain implements ModInitializer {

		@Override
		public void onInitialize() {

			//Inicializa la clase de los comandos
			RegisterMyCommands.init();
			GachaItem.init();

	}
}
