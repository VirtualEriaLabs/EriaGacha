package net.eriagacha.services.gachapool;

import net.eriagacha.GachaBagRegister;
import net.eriagacha.utils.GachaUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GachaPoolServiceFactory {

  private GachaPoolServiceFactory() {
  }

  public static GachaPoolService getInstance(int gachaRawId) {
    if (Item.getRawId(GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return new GachaPoolService(GachaBagRegister.EXPENSIVE_GACHA_ENTRY_LIST,
          new ItemStack(Item.byRawId(gachaRawId))
      );
    } else if (Item.getRawId(GachaUtils.CHEAP_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return new GachaPoolService(GachaBagRegister.CHEAP_GACHA_ENTRY_LIST,
          new ItemStack(Item.byRawId(gachaRawId)));
    }
    return null;
  }

}
