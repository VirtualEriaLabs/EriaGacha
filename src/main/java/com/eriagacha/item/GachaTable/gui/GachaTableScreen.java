package com.eriagacha.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class GachaTableScreen extends CottonInventoryScreen<GachaTableGui> {

  public static final ScreenRegistry.Factory<GachaTableGui, GachaTableScreen> FACTORY =
      (desc, playerInventory, title) -> new GachaTableScreen(desc, playerInventory.player, title);

  public GachaTableScreen(GachaTableGui description, PlayerEntity player, Text title) {
    super(description, player, title);
  }
}
