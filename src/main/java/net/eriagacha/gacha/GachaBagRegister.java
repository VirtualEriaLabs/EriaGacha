package net.eriagacha.gacha;

import java.util.Arrays;
import java.util.List;
import net.eriagacha.giveable.GivableItemStack;
import net.eriagacha.giveable.GivableStatusEffect;
import net.eriagacha.api.MinecraftGivable;
import net.eriagacha.register.RegisterItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;

public class GachaBagRegister {

  public static final WeightedRandomBag CHEAP_GACHA_BAG = new WeightedRandomBag();

  public static final WeightedRandomBag EXPENSIVE_GACHA_BAG = new WeightedRandomBag();

  private GachaBagRegister() {
  }

  public static void registerItems() {
    MinecraftGivable diamond = GivableItemStack.builder()
        .itemStack(new ItemStack(Items.DIAMOND, 5))
        .build();
    MinecraftGivable torch = GivableItemStack.builder()
        .itemStack(new ItemStack(Items.TORCH, 12))
        .build();
    MinecraftGivable stone = GivableItemStack.builder()
        .itemStack(new ItemStack(Items.STONE, 32))
        .build();
    MinecraftGivable diamondPickaxe = GivableItemStack.builder()
        .itemStack(new ItemStack(Items.DIAMOND_PICKAXE, 1))
        .build();
    MinecraftGivable adeptusTemptation = GivableItemStack.builder()
        .itemStack(new ItemStack(RegisterItems.ADEPTUS_TEMPTATION, 1))
        .build();
    MinecraftGivable haste = GivableStatusEffect.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .name("El efecto de rapidez!")
        .build();
    MinecraftGivable speed = GivableStatusEffect.builder()
        .statusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20000, 3))
        .name("El efecto velocidad!")
        .build();

    List<GachaRoll> expensiveGachaRoll = Arrays.asList(
        GachaRoll.builder()
            .weight(10)
            .name("Diamantes bitch")
            .rewards(Arrays.asList(diamond))
            .rollMessage(new TranslatableText("Diamantes Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(20)
            .name("Torch bitch")
            .rewards(Arrays.asList(torch))
            .rollMessage(new TranslatableText("Torch Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(30)
            .name("Stone bitch")
            .rewards(Arrays.asList(stone))
            .rollMessage(new TranslatableText("Stone Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(5)
            .name("DiamondPickaxe bitch")
            .rewards(Arrays.asList(diamondPickaxe))
            .rollMessage(new TranslatableText("DiamondPickaxe Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(15)
            .name("AdeptusTemptation bitch")
            .rewards(Arrays.asList(adeptusTemptation))
            .rollMessage(new TranslatableText("AdeptusTemptation Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(15)
            .name("Haste bitch")
            .rewards(Arrays.asList(haste))
            .rollMessage(new TranslatableText("Haste Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(3)
            .name("Speed bitch")
            .rewards(Arrays.asList(speed))
            .rollMessage(new TranslatableText("Speed Bitch"))
            .build()
    );

    EXPENSIVE_GACHA_BAG.addEntries(expensiveGachaRoll);

    List<GachaRoll> cheapGachaRolls = Arrays.asList(
        GachaRoll.builder()
            .weight(10)
            .name("Diamantes bitch")
            .rewards(Arrays.asList(diamond))
            .rollMessage(new TranslatableText("Diamantes Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(15)
            .name("AdeptusTemptation bitch")
            .rewards(Arrays.asList(adeptusTemptation))
            .rollMessage(new TranslatableText("AdeptusTemptation Bitch"))
            .build(),
        GachaRoll.builder()
            .weight(3)
            .name("Speed bitch")
            .rewards(Arrays.asList(speed))
            .rollMessage(new TranslatableText("Speed Bitch"))
            .build()
    );

    CHEAP_GACHA_BAG.addEntries(cheapGachaRolls);
  }

}
