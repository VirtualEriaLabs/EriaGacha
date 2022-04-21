package com.eriagacha;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;

import com.eriagacha.item.kartografi.gui.KartografiGui;
import com.eriagacha.item.kartografi.gui.KartografiScreen;
import com.eriagacha.register.RegisterBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EriaGachaClientMain implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    ScreenRegistry.<KartografiGui, KartografiScreen>register(SCREEN_HANDLER_BENCH_TYPE, (handler, inventory, title) -> new KartografiScreen(handler, inventory.player, title));
    RegisterBlockEntityRenderer.init();
  }
}
