package com.eriagacha;

import com.eriagacha.gui.GachaTableScreen;
import com.eriagacha.register.RegisterItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EriaGachaClientMain implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    /*
    ScreenRegistry.<GachaTableScreenHandler, GachaTableScreen>register(
        RegisterItems.SCREEN_HANDLER_INVENTORY_TYPE,
        (gui, inventory, title) -> new GachaTableScreen(gui, inventory.player, title));

     */
    ScreenRegistry.register(RegisterItems.SCREEN_HANDLER_INVENTORY_TYPE, GachaTableScreen::new);
  }
}
