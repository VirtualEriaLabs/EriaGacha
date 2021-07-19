package net.eriagacha;

import net.fabricmc.api.ModInitializer;


public class EriaGachaMain implements ModInitializer {

		@Override
		public void onInitialize() {

			RegisterCommands.init();
			RegisterItems.init();

	}
}
