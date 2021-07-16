package net.eriagacha;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GachaItem {

  //TODO : Investigar PlayerItemConsumeEvent
  private static final Item GACHA_COOKIE = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(3));

  public static void init(){

    Registry.register(Registry.ITEM, new Identifier("gacheria", "gacha_cookie"), GACHA_COOKIE);

  }
}
