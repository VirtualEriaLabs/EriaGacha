package net.eriagacha.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;

public class AcquaintFate extends CustomItem {

  public AcquaintFate() {
    super(new Settings()
        .group(ItemGroup.MISC).food(
            new FoodComponent.Builder().hunger(1).
                saturationModifier(1f).alwaysEdible().build()));
  }
}
