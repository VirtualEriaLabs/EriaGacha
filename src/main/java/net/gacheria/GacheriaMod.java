package net.gacheria;

import net.fabricmc.api.ModInitializer;


public class GacheriaMod implements ModInitializer {

		@Override
		public void onInitialize() {

			//Inicializa la clase de los comandos
			RegisterMyCommands.init();
			GachaItem.init();

	}
}
