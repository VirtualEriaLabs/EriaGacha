package com.eriagacha.item;

import com.eriagacha.register.RegisterItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EssenceOreItem extends BlockItem {

  public EssenceOreItem() {
    super(RegisterItems.EssenceOre_BLOCK,
        new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
  }
}
