package com.eriagacha.item.GachaFurnace;

import com.eriagacha.register.RegisterItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class GachaFurnaceItem extends BlockItem {

  public GachaFurnaceItem() {
    super(RegisterItems.GACHA_FURNACE, new Settings().group(ItemGroup.DECORATIONS));

  }
}
