package com.eriagacha.item.gachabench.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class GachaBenchScreen extends CottonInventoryScreen<GachaBenchGui> {

  public GachaBenchScreen(GachaBenchGui description,
                          PlayerEntity player) {
    super(description, player);
  }

  public GachaBenchScreen(GachaBenchGui description, PlayerEntity player, Text title) {
    super(description, player, title);
  }
}
