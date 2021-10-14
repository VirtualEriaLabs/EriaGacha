package com.eriagacha.item.gachabench;

import static com.eriagacha.register.RegisterBlock.GACHA_BENCH;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class GachaBenchItem extends BlockItem {
  public GachaBenchItem() {
    super(GACHA_BENCH, new Item.Settings().group(ItemGroup.DECORATIONS));
  }
}
