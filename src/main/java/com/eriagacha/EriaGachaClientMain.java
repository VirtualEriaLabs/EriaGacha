package com.eriagacha;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;
import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_FURNACE_TYPE;
import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_TABLE_TYPE;

import com.eriagacha.item.GachaFurnace.gui.GachaFurnaceScreen;
import com.eriagacha.item.GachaTable.gui.GachaTableScreen;
import com.eriagacha.item.gachabench.gui.GachaBenchGui;
import com.eriagacha.item.gachabench.gui.GachaBenchScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EriaGachaClientMain implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    ScreenRegistry.register(SCREEN_HANDLER_TABLE_TYPE, GachaTableScreen.FACTORY);
    ScreenRegistry.register(SCREEN_HANDLER_FURNACE_TYPE, GachaFurnaceScreen.FACTORY);
    ScreenRegistry.<GachaBenchGui,GachaBenchScreen>register(SCREEN_HANDLER_BENCH_TYPE, (handler, inventory, title) -> new GachaBenchScreen(handler, inventory.player, title));
  }
}
