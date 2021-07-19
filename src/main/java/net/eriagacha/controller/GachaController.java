package net.eriagacha.controller;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.eriagacha.RegisterItems;
import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class GachaController {


  public static final WeightedRandomBag GACHERIA_LIST = new WeightedRandomBag();
  public static final ItemStack GACHA_REQUIEREMENT = new ItemStack(RegisterItems.INTERTWINED_FATE);

  private GachaController() {
  }


  /**
   * Method that register the items in the gacha
   */
  public static void loadTheGacha(){
    System.out.println(new TranslatableText("block.minecraft.torch"));
    System.out.println(new TranslatableText("block.minecraft.torch").getString());
    System.out.println(new TranslatableText("block.minecraft.torch").copy());
    GachaObjectModel diamond = new GachaObjectModel(Items.DIAMOND, "Diamante(s)!", 5);
    GachaObjectModel torch = new GachaObjectModel(Items.TORCH, "Antorcha(s)!", 12);
    GachaObjectModel stone = new GachaObjectModel(Items.STONE, "Piedra(s)!", 32);
    GachaObjectModel diamondPickaxe = new GachaObjectModel(Items.DIAMOND_PICKAXE, "Pico de diamante!", 1);
    GachaObjectModel adeptusTemptation = new GachaObjectModel(RegisterItems.ADEPTUS_TEMPTATION, 1);
    GachaObjectModel haste = new GachaObjectModel( new StatusEffectInstance(StatusEffects.HASTE, 20000, 3), "El efecto Rapidez!");
    GachaObjectModel speed = new GachaObjectModel( new StatusEffectInstance(StatusEffects.SPEED, 20000, 3), "El efecto Velocidad!");

    GACHERIA_LIST.addEntry(diamond, 10);
    GACHERIA_LIST.addEntry(torch, 20);
    GACHERIA_LIST.addEntry(stone, 30);
    GACHERIA_LIST.addEntry(diamondPickaxe, 20);
    GACHERIA_LIST.addEntry(haste, 15);
    GACHERIA_LIST.addEntry(speed, 15);
    GACHERIA_LIST.addEntry(adeptusTemptation, 100);
  }



  //TODO: Messages in different langs
  /**
   * Method that return a reward from the gacha to an user, requieres a "CommandContext" class with the player context
   * @param ctx
   * @return
   * @throws CommandSyntaxException
   */
  public static int giveItem(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException{
    final ServerCommandSource source = ctx.getSource();
    GachaObjectModel obj = GACHERIA_LIST.getRandom();
    final PlayerEntity self = source.getPlayer(); // If not a player than the command ends
    if(self.getInventory().contains(GACHA_REQUIEREMENT))
    {
      if(obj.getRewardType()==1){
        if (self.getInventory().insertStack(new ItemStack(obj.getItem(), obj.getItemQuanty()))) {
          //ctx.getSource().sendFeedback(new LiteralText( "Has obtenido "+ obj.getItemQuanty() + " " + obj.getRewardName()), false);
          ctx.getSource().sendFeedback(new LiteralText( "Has obtenido "+ obj.getItemQuanty() + " " + new TranslatableText(obj.getItem().getTranslationKey()).getString()) , false);
          self.getInventory().removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT),1);
        }else{
          throw new SimpleCommandExceptionType(new TranslatableText("Tienes el inventario lleno Puto")).create();
        }
      }else if(obj.getRewardType()==2){
        self.setStatusEffect(obj.getStatusEffectInstance(), self);
        ctx.getSource().sendFeedback(new LiteralText( "Has obtenido " + obj.getRewardName()), false);
        self.getInventory().removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT),1);
      }else{
        ctx.getSource().sendFeedback(new LiteralText( "Ha ocurrido un error inesperado, contacta con un administrador si se repite este error :3 "), false);
      }
    }else{
      throw new SimpleCommandExceptionType(new TranslatableText("Te falta una " + GACHA_REQUIEREMENT
          .getTranslationKey())).create();
    }
    return 1;
  }
}
