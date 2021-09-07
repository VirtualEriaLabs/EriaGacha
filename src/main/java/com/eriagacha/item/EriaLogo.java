package com.eriagacha.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EriaLogo extends Item {

  public EriaLogo() {
    super(new Settings().group(ItemGroup.DECORATIONS).fireproof().maxCount(1));
  }
}
