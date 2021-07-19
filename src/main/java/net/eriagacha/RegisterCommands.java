package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

public class RegisterCommands implements Command<Object> {



  @Override
  public int run(CommandContext<Object> context) {
    return 0;
  }


  /**
   * load the dependencies
   */
  public static void init() {
    GachaLogic.loadTheGacha();
    registerCommands();

  }


  /**
   * Metodo para registrar los comandos
   */
  public static void registerCommands(){

    // common gacha command
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha")
          .executes(context -> {
            GachaLogic.giveItem(context);
            return 1;
          }));
    });

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("commision")
          .executes(context -> {
            Commissions.getCommision(context);
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



}



