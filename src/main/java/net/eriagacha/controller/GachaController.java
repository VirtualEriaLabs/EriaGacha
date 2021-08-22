package net.eriagacha.controller;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.eriagacha.RegisterItems;
import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.models.GachaObjectModelItem;
import net.eriagacha.models.GachaObjectModelStatus;
import net.eriagacha.utils.GachaUtils;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;


public class GachaController {

  public static final WeightedRandomBag CHEAP_GACHA_ENTRY_LIST = new WeightedRandomBag();
  public static final WeightedRandomBag EXPENSIVE_GACHA_ENTRY_LIST = new WeightedRandomBag();
  private GachaController() {}

  /**
   * Method that register the items in the gacha
   */
  public static void loadTheGacha() {
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

  /**
   * Method that return a reward from the gacha to an user, requieres a "CommandContext" class with the player context
   *
   * @param ctx
   * @return
   * @throws CommandSyntaxException
   */
  public static int giveGachaReward(CommandContext<ServerCommandSource> ctx, ItemStack moneyCondition)
      throws CommandSyntaxException {

    boolean moneyConditionsMet =
        ctx.getSource().getPlayer().getInventory().contains(moneyCondition);

    if (!moneyConditionsMet) {
      String missingText = new TranslatableText("text.eriagacha.missing").getString();
      String moneyConditionText = new TranslatableText(moneyCondition.getTranslationKey()).getString();
      throw new SimpleCommandExceptionType(new LiteralText(
          String.format("%s %s",
              missingText,
              moneyConditionText)
      )).create();
    }

    if(moneyCondition== GachaUtils.CHEAP_GACHA_REQUIEREMENT)
      CHEAP_GACHA_ENTRY_LIST.getRandom().reward(ctx);
    if(moneyCondition== GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT)
      EXPENSIVE_GACHA_ENTRY_LIST.getRandom().reward(ctx);

    return 1;
  }
}
