package com.eriagacha.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;

public class AcquaintFateItem extends CustomItem {

  public AcquaintFateItem() {
    super(new Settings()
        .group(ItemGroup.MISC).food(
            new FoodComponent.Builder().hunger(1).
                saturationModifier(1f).alwaysEdible().build()));
  }
}
