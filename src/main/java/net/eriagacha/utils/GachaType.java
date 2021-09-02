package net.eriagacha.utils;

import net.minecraft.item.Item;

public class GachaType {
  public static int EXPENSIVE_GACHA_ID = 1;
  public static int CHEAP_GACHA_ID = 2;

  public static int getGachaType(int GachaRawId) {
    if (GachaRawId == Item.getRawId(NameSpaces.GachaItems.EXPENSIVE_GACHA_REQUIEREMENT.getItem())) {
      return 1;
    } else if (GachaRawId ==
        Item.getRawId(NameSpaces.GachaItems.CHEAP_GACHA_REQUIEREMENT.getItem())) {
      return 2;
    }

    return 0;
  }
}
