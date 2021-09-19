package com.eriagacha.item.GachaTable;

import static com.eriagacha.register.RegisterBlock.GACHA_TABLE;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class GachaTableItem extends BlockItem {

  public GachaTableItem() {
    super(GACHA_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS));

  }
}
