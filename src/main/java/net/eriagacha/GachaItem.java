package net.eriagacha;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GachaItem {

  //TODO : Investigar PlayerItemConsumeEvent para que se ejecute el comando al comer
  public static final Item GACHA_COOKIE = new Item(new Item.Settings()
      .group(ItemGroup.MISC).food(new FoodComponent.Builder().hunger(1).saturationModifier(1f).snack().alwaysEdible().build()));

  public static void init(){

    Registry.register(Registry.ITEM, new Identifier("eriagacha", "gacha_cookie"), GACHA_COOKIE);
  }
}
