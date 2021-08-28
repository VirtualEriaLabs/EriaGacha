package net.eriagacha.gachapool;

import net.eriagacha.utils.NameSpaces;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GachaPoolServiceFactory {

  private GachaPoolServiceFactory() {
  }

  public static GachaPoolService getInstance(int gachaRawId) {
    if (Item.getRawId(NameSpaces.GachaItems.EXPENSIVE_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return new GachaPoolService(GachaBagRegister.EXPENSIVE_GACHA_ENTRY_LIST,
          new ItemStack(Item.byRawId(gachaRawId))
      );
    } else if (Item.getRawId(NameSpaces.GachaItems.CHEAP_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return new GachaPoolService(GachaBagRegister.CHEAP_GACHA_ENTRY_LIST,
          new ItemStack(Item.byRawId(gachaRawId)));
    }
    return null;
  }

}
