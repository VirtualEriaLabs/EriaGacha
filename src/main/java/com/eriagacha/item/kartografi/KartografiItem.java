package com.eriagacha.item.kartografi;

import static com.eriagacha.register.RegisterBlock.GACHA_BENCH;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KartografiItem extends BlockItem {
  public KartografiItem() {
    super(GACHA_BENCH, new Item.Settings().group(ItemGroup.DECORATIONS));
  }
}
