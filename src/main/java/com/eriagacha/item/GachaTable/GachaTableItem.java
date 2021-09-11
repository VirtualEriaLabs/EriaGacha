package com.eriagacha.item.GachaTable;

import com.eriagacha.register.RegisterItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class GachaTableItem extends BlockItem {

  public GachaTableItem() {
    super(RegisterItems.GACHA_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS));

  }
}
