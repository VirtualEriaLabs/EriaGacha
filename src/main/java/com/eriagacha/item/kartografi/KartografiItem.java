package com.eriagacha.item.kartografi;

import static com.eriagacha.register.RegisterBlock.KARTOGRAFI;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KartografiItem extends BlockItem {
  public KartografiItem() {
    super(KARTOGRAFI, new Item.Settings().group(ItemGroup.DECORATIONS));
  }
}
