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
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

public class GachaController {

  public static final WeightedRandomBag GACHERIA_LIST = new WeightedRandomBag();

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

    GACHERIA_LIST.addEntry(diamond);
    GACHERIA_LIST.addEntry(torch);
    GACHERIA_LIST.addEntry(stone);
    GACHERIA_LIST.addEntry(diamondPickaxe);
    GACHERIA_LIST.addEntry(haste);
    GACHERIA_LIST.addEntry(speed);
    GACHERIA_LIST.addEntry(adeptusTemptation);
  }

  /**
   * Method that return a reward from the gacha to an user, requieres a "CommandContext" class with the player context
   *
   * @param ctx
   * @return
   * @throws CommandSyntaxException
   */
  public static int giveItem(CommandContext<ServerCommandSource> ctx)
      throws CommandSyntaxException {

    boolean moneyConditionsMet =
        ctx.getSource().getPlayer().getInventory().contains(GachaUtils.GACHA_REQUIEREMENT);

    if (!moneyConditionsMet) {
      throw new SimpleCommandExceptionType(
          new TranslatableText("Te falta una " + GachaUtils.GACHA_REQUIEREMENT
              .getTranslationKey())).create();
    }

    GACHERIA_LIST.getRandom().reward(ctx);

    return 1;
  }



}
