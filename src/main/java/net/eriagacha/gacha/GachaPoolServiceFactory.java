package net.eriagacha.gacha;

import static net.eriagacha.event.EventHandler.GACHA_ROLL_EVENT;

import net.eriagacha.GachaRewarder;
import net.eriagacha.register.RegisterItems;
import net.eriagacha.utils.NameSpaces;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GachaPoolServiceFactory {

  private GachaPoolServiceFactory() {
  }

  public static GachaPoolService getInstance(int gachaRawId) {
    if (Item.getRawId(NameSpaces.GachaItems.EXPENSIVE_GACHA_REQUIEREMENT.getItem()) == gachaRawId) {
      return GachaPoolService.builder()
          .rollCost(new ItemStack(RegisterItems.INTERTWINED_FATE, 1))
          .gachaRollEvent(GACHA_ROLL_EVENT)
          .minecraftRewarder((player, reward) -> player.stream()
              .forEach(serverPlayerEntity -> reward.apply(serverPlayerEntity)))
          .weightedRandomBag(GachaBagRegister.EXPENSIVE_GACHA_BAG)
          .build();
    } else if (Item.getRawId(NameSpaces.GachaItems.CHEAP_GACHA_REQUIEREMENT.getItem()) ==
        gachaRawId) {
      return GachaPoolService.builder()
          .rollCost(new ItemStack(RegisterItems.ACQUAINT_FATE, 1))
          .gachaRollEvent(GACHA_ROLL_EVENT)
          .minecraftRewarder(new GachaRewarder())
          .weightedRandomBag(GachaBagRegister.CHEAP_GACHA_BAG)
          .build();
    }
    return null;
  }

}
