package net.eriagacha.gachapool;

import net.eriagacha.models.GachaRewardModel;
import net.eriagacha.register.RegisterItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;

public class GachaBagRegister {

  public static final WeightedRandomBag CHEAP_GACHA_BAG = new WeightedRandomBag();

  public static final WeightedRandomBag EXPENSIVE_GACHA_BAG = new WeightedRandomBag();

  private GachaBagRegister() {
  }

  public static void registerItems() {
    GachaRewardModel diamond = GachaRewardModel.builder()
        .item(Items.DIAMOND)
        .itemQuantity(5)
        .weight(10)
        .build();

    GachaRewardModel torch = GachaRewardModel.builder()
        .item(Items.TORCH)
        .itemQuantity(12)
        .weight(20)
        .build();

    GachaRewardModel stone = GachaRewardModel.builder()
        .item(Items.STONE)
        .itemQuantity(32)
        .weight(30)
        .build();

    GachaRewardModel diamondPickaxe = GachaRewardModel.builder()
        .item(Items.DIAMOND_PICKAXE)
        .itemQuantity(1)
        .weight(5)
        .build();

    GachaRewardModel adeptusTemptation = GachaRewardModel.builder()
        .item(RegisterItems.ADEPTUS_TEMPTATION)
        .itemQuantity(1)
        .weight(15)
        .build();

    GachaRewardModel haste = GachaRewardModel.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .rewardName("El efecto de rapidez!")
        .weight(15)
        .build();

    GachaRewardModel speed = GachaRewardModel.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .rewardName("El efecto velocidad!")
        .weight(3)
        .build();


    CHEAP_GACHA_BAG.addEntry(diamond);
    CHEAP_GACHA_BAG.addEntry(torch);
    CHEAP_GACHA_BAG.addEntry(stone);
    CHEAP_GACHA_BAG.addEntry(diamondPickaxe);
    CHEAP_GACHA_BAG.addEntry(haste);
    CHEAP_GACHA_BAG.addEntry(speed);
    CHEAP_GACHA_BAG.addEntry(adeptusTemptation);

    EXPENSIVE_GACHA_BAG.addEntry(adeptusTemptation);
  }

}
