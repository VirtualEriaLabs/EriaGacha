package com.eriagacha.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class PrimogemItem extends Item {

  public PrimogemItem() {
    super(new Item.Settings().group(ItemGroup.MISC).food(
        new FoodComponent.Builder().hunger(1).saturationModifier(1f).snack().alwaysEdible()
            .build()));
  }
}
