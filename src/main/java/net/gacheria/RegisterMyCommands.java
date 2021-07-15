package net.gacheria;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class RegisterMyCommands implements Command<Object> {


  public static final WeightedRandomBag GACHERIA_LIST = new WeightedRandomBag();

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
    GachaObject diamond = new GachaObject(Items.DIAMOND);
    GachaObject torch = new GachaObject(Items.TORCH);
    GACHERIA_LIST.addEntry(diamond, 10);
    GACHERIA_LIST.addEntry(torch, 90);
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

    /*
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("willydiosHablo")
          .then(RequiredArgumentBuilder.argument("message", StringArgumentType.string()))
          .executes(context -> {
            String message = StringArgumentType.getString(context, "message");
            context.getSource().sendFeedback(new LiteralText(
                message + " " + context.getSource().getPlayer().getDisplayName().asString()), true);
            return 1;
          }));
    });
    */
  }

  /**
   *
   */
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
    GachaObject obj = GACHERIA_LIST.getRandom();
    ctx.getSource().sendFeedback(new LiteralText( "Has obtenido " + obj.getItem().toString()), false);
    final PlayerEntity self = source.getPlayer(); // If not a player than the command ends
    if (!self.getInventory().insertStack(new ItemStack(obj.getItem()))) {
      throw new SimpleCommandExceptionType(new TranslatableText("inventory.isfull")).create();
    }
    return 1;
  }
}



