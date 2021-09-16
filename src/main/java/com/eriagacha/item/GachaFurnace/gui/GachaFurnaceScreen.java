package com.eriagacha.item.GachaFurnace.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class GachaFurnaceScreen extends CottonInventoryScreen<GachaFurnaceGui> {

  public static final ScreenRegistry.Factory<GachaFurnaceGui, GachaFurnaceScreen> FACTORY =
      (desc, playerInventory, title) -> new GachaFurnaceScreen(desc, playerInventory.player, title);

  public GachaFurnaceScreen(GachaFurnaceGui description, PlayerEntity player, Text title) {
    super(description, player, title);
  }
}
