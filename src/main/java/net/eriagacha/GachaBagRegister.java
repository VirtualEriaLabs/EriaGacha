package net.eriagacha;

import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.models.GachaObjectModelItem;
import net.eriagacha.models.GachaObjectModelStatus;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;

public class GachaBagRegister {

  public static final WeightedRandomBag CHEAP_GACHA_ENTRY_LIST = new WeightedRandomBag();

  public static final WeightedRandomBag EXPENSIVE_GACHA_ENTRY_LIST = new WeightedRandomBag();

  public static void registerItems() {
    final GachaObjectModel diamond = GachaObjectModelItem.builder()
        .item(Items.DIAMOND)
        .itemQuantity(5)
        .weight(10)
        .build();

    final GachaObjectModel torch = GachaObjectModelItem.builder()
        .item(Items.TORCH)
        .itemQuantity(12)
        .weight(20)
        .build();

    final GachaObjectModel stone = GachaObjectModelItem.builder()
        .item(Items.STONE)
        .itemQuantity(32)
        .weight(30)
        .build();

    final GachaObjectModel diamondPickaxe = GachaObjectModelItem.builder()
        .item(Items.DIAMOND_PICKAXE)
        .itemQuantity(1)
        .weight(5)
        .build();

    final GachaObjectModel adeptusTemptation = GachaObjectModelItem.builder()
        .item(RegisterItems.ADEPTUS_TEMPTATION)
        .itemQuantity(1)
        .weight(15)
        .build();

    final GachaObjectModel haste = GachaObjectModelStatus.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .rewardName("El efecto de rapidez!")
        .weight(15)
        .build();

    final GachaObjectModel speed = GachaObjectModelStatus.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .rewardName("El efecto velocidad!")
        .weight(3)
        .build();

    CHEAP_GACHA_ENTRY_LIST.addEntry(diamond);
    CHEAP_GACHA_ENTRY_LIST.addEntry(torch);
    CHEAP_GACHA_ENTRY_LIST.addEntry(stone);
    CHEAP_GACHA_ENTRY_LIST.addEntry(diamondPickaxe);
    CHEAP_GACHA_ENTRY_LIST.addEntry(haste);
    CHEAP_GACHA_ENTRY_LIST.addEntry(speed);
    CHEAP_GACHA_ENTRY_LIST.addEntry(adeptusTemptation);

    EXPENSIVE_GACHA_ENTRY_LIST.addEntry(adeptusTemptation);
  }

}
