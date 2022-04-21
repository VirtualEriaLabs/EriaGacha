package com.eriagacha.item.kartografi.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class KartografiScreen extends CottonInventoryScreen<KartografiGui> {

  public KartografiScreen(KartografiGui description,
                          PlayerEntity player) {
    super(description, player);
  }

  public KartografiScreen(KartografiGui description, PlayerEntity player, Text title) {
    super(description, player, title);
  }
}
