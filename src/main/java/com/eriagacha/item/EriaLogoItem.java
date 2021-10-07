package com.eriagacha.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EriaLogoItem extends Item {

  public EriaLogoItem() {
    super(new Settings().group(ItemGroup.DECORATIONS).fireproof().maxCount(1));
  }
}
