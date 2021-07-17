package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class RegisterMyCommands implements Command<Object> {


  public static final WeightedRandomBag GACHERIA_LIST = new WeightedRandomBag();
  public static final ItemStack REQUIEREMENT = new ItemStack(Items.EMERALD);
  @Override
  public int run(CommandContext<Object> context) {
    return 0;
  }


  /**
   * Debe de ser ejecutado para que funcione el programa
   */
  public static void init() {
    loadTheGacha();
    registerCommands();

  }

  /**
   * Metodo donde se añaden los objetos al gacha con su rareza
   */
  public static void loadTheGacha(){
    GachaObject diamond = new GachaObject(Items.DIAMOND, "Diamante(s)!", 5);
    GachaObject torch = new GachaObject(Items.TORCH, "Antorcha(s)!", 12);
    GachaObject stone = new GachaObject(Items.STONE, "Piedra(s)!", 32);
    GachaObject diamond_picaxe = new GachaObject(Items.DIAMOND_PICKAXE, "Pico de diamante!", 1);
    GachaObject haste = new GachaObject( new StatusEffectInstance(StatusEffects.HASTE, 200), "Haste por 8 segundos!");

    GACHERIA_LIST.addEntry(diamond, 20);
    GACHERIA_LIST.addEntry(torch, 30);
    GACHERIA_LIST.addEntry(stone, 50);
    GACHERIA_LIST.addEntry(diamond_picaxe, 25);
    GACHERIA_LIST.addEntry(haste, 50);

  }

  /**
   * Metodo para registrar los comandos
   */
  public static void registerCommands(){

    // Comando que da lugar al gacha
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha")
          .executes(context -> {
            giveItem(context);
            return 1;
          }));
    });
  }

  /*
  public static int serverMessage(CommandContext<ServerCommandSource> ctx) throws
      CommandSyntaxException {
    final ServerCommandSource source = ctx.getSource();
    System.out.println("Soy el input " + StringArgumentType.getString(ctx, "message"));
    String message = StringArgumentType.getString(ctx, "message");
    ctx.getSource().sendFeedback(
        new LiteralText(message + "" + ctx.getSource().getPlayer().getDisplayName().asString()),
        false);
    //ctx.getSource().sendFeedback(new LiteralText( ctx.getSource().getPlayer().getDisplayName().asString()), false);
    return 1;
  }
  */


  /**
   * Metodo para dar un item del Gacha al usuario, requiere de una clase "CommandContext"
   * @param ctx
   * @return
   * @throws CommandSyntaxException
   */
  public static int giveItem(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException{
    final ServerCommandSource source = ctx.getSource();
    //Coge un objeto aleatorio del Gacha
    GachaObject obj = GACHERIA_LIST.getRandom();
    final PlayerEntity self = source.getPlayer(); // If not a player than the command ends
    if(self.getInventory().contains(REQUIEREMENT))
    {
      if(obj.rewardType==1){
        //Se añade el item al usuario
        if (self.getInventory().insertStack(new ItemStack(obj.getItem(), obj.itemQuanty))) {
          //Manda el mensaje de la recompensa
          ctx.getSource().sendFeedback(new LiteralText( "Has obtenido "+ obj.itemQuanty + " " + obj.rewardName), false);
          //Se elimina el requerimiento para tirar al gacha
          self.getInventory().removeStack(self.getInventory().getSlotWithStack(REQUIEREMENT),1);
        }else{
          throw new SimpleCommandExceptionType(new TranslatableText("Tienes el inventario lleno Puto")).create();
        }
      }else if(obj.rewardType==2){
        //Se añade el efecto al usuario
        self.setStatusEffect(obj.statusEffectInstance, self);
        //Manda el mensaje de la recompensa
        ctx.getSource().sendFeedback(new LiteralText( "Has obtenido " + obj.rewardName), false);
        //Se elimina el requerimiento para tirar al gacha
        self.getInventory().removeStack(self.getInventory().getSlotWithStack(REQUIEREMENT),1);
      }else {
        ctx.getSource().sendFeedback(new LiteralText( "Ha ocurrido un error inesperado, contacta con un administrador si se repite este error :3 "), false);
      }
    }else
    {
      throw new SimpleCommandExceptionType(new TranslatableText("Te falta una " + REQUIEREMENT.getTranslationKey())).create();
    }
    return 1;
  }
}



