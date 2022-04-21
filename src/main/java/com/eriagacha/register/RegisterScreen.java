package com.eriagacha.register;

import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.kartografi.gui.KartografiGui;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class RegisterScreen {
  public static ScreenHandlerType<KartografiGui> SCREEN_HANDLER_BENCH_TYPE;

  public static void init() {

    SCREEN_HANDLER_BENCH_TYPE = ScreenHandlerRegistry.registerExtended(
        id("kartografi_gui"),
        (syncId, inventory, buf) -> new KartografiGui(
            syncId,
            inventory,
            ScreenHandlerContext.create(inventory.player.world, buf.readBlockPos())));
  }
}
