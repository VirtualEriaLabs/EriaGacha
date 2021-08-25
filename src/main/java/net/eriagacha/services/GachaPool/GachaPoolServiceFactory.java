package net.eriagacha.services.GachaPool;

import net.eriagacha.GachaBagRegister;
import net.eriagacha.utils.GachaUtils;
import net.minecraft.item.Item;

public class GachaPoolServiceFactory {

  public static GachaPoolService getInstance(int gachaRawId) {
    if (Item.getRawId(GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return new ExpensiveGachaPoolService(GachaBagRegister.EXPENSIVE_GACHA_ENTRY_LIST);
    }
    else if(Item.getRawId(GachaUtils.CHEAP_GACHA_REQUIEREMENT.getItem()) == gachaRawId){
      return new CheapGachaPoolService(GachaBagRegister.CHEAP_GACHA_ENTRY_LIST);
    }
    return null;
  }

}
