package com.eriagacha;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_FURNACE_TYPE;
import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_TABLE_TYPE;

import com.eriagacha.item.GachaFurnace.gui.GachaFurnaceScreen;
import com.eriagacha.item.GachaTable.gui.GachaTableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EriaGachaClientMain implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    ScreenRegistry.register(SCREEN_HANDLER_TABLE_TYPE, GachaTableScreen.FACTORY);
    ScreenRegistry.register(SCREEN_HANDLER_FURNACE_TYPE, GachaFurnaceScreen.FACTORY);
  }
}
