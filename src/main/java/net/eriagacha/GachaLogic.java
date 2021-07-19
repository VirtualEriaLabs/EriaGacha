package net.eriagacha;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class GachaLogic {


  public static final WeightedRandomBag GACHERIA_LIST = new WeightedRandomBag();
  public static final ItemStack GACHA_REQUIEREMENT = new ItemStack(GachaItem.GACHA_COOKIE);

  /**
   * Metodo donde se añaden los objetos al gacha con su rareza
   */
  public static void loadTheGacha(){
    GachaObject diamond = new GachaObject(Items.DIAMOND, "Diamante(s)!", 5);
    GachaObject torch = new GachaObject(Items.TORCH, "Antorcha(s)!", 12);
    GachaObject stone = new GachaObject(Items.STONE, "Piedra(s)!", 32);
    GachaObject diamond_picaxe = new GachaObject(Items.DIAMOND_PICKAXE, "Pico de diamante!", 1);
    GachaObject eria_logo = new GachaObject(GachaItem.ERIA_LOGO, "COMIDA TOCHA!", 1);
    GachaObject haste = new GachaObject( new StatusEffectInstance(StatusEffects.HASTE, 20000, 3), "El efecto Rapidez!");
    GachaObject speed = new GachaObject( new StatusEffectInstance(StatusEffects.SPEED, 20000, 3), "El efecto Velocidad!");

    GACHERIA_LIST.addEntry(diamond, 10);
    GACHERIA_LIST.addEntry(torch, 20);
    GACHERIA_LIST.addEntry(stone, 30);
    GACHERIA_LIST.addEntry(diamond_picaxe, 20);
    GACHERIA_LIST.addEntry(haste, 15);
    GACHERIA_LIST.addEntry(speed, 15);
    GACHERIA_LIST.addEntry(eria_logo, 5);
  }


  /**
   * Metodo para dar una recompensa del Gacha al usuario, requiere de una clase "CommandContext"
   * @param ctx
   * @return
   * @throws CommandSyntaxException
   */
  public static int giveItem(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException{
    final ServerCommandSource source = ctx.getSource();
    GachaObject obj = GACHERIA_LIST.getRandom();
    final PlayerEntity self = source.getPlayer(); // If not a player than the command ends
    if(self.getInventory().contains(GACHA_REQUIEREMENT))
    {
      if(obj.rewardType==1){
        if (self.getInventory().insertStack(new ItemStack(obj.getItem(), obj.itemQuanty))) {
          ctx.getSource().sendFeedback(new LiteralText( "Has obtenido "+ obj.itemQuanty + " " + obj.rewardName), false);
          self.getInventory().removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT),1);
        }else{
          throw new SimpleCommandExceptionType(new TranslatableText("Tienes el inventario lleno Puto")).create();
        }
      }else if(obj.rewardType==2){
        self.setStatusEffect(obj.statusEffectInstance, self);
        ctx.getSource().sendFeedback(new LiteralText( "Has obtenido " + obj.rewardName), false);
        self.getInventory().removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT),1);
      }else {
        ctx.getSource().sendFeedback(new LiteralText( "Ha ocurrido un error inesperado, contacta con un administrador si se repite este error :3 "), false);
      }
    }else
    {
      throw new SimpleCommandExceptionType(new TranslatableText("Te falta una " + GACHA_REQUIEREMENT
          .getTranslationKey())).create();
    }
    return 1;
  }
}
