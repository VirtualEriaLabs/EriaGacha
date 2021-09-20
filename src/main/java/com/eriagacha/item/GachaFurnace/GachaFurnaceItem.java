package com.eriagacha.item.GachaFurnace;

import static com.eriagacha.register.RegisterBlock.GACHA_FURNACE;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class GachaFurnaceItem extends BlockItem {
  public GachaFurnaceItem() {
    super(GACHA_FURNACE, new Settings().group(ItemGroup.DECORATIONS));
  }
}
