package com.eriagacha;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;

import com.eriagacha.item.gachabench.gui.GachaBenchGui;
import com.eriagacha.item.gachabench.gui.GachaBenchScreen;
import com.eriagacha.register.RegisterBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EriaGachaClientMain implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    ScreenRegistry.<GachaBenchGui,GachaBenchScreen>register(SCREEN_HANDLER_BENCH_TYPE, (handler, inventory, title) -> new GachaBenchScreen(handler, inventory.player, title));
    RegisterBlockEntityRenderer.init();
  }
}
