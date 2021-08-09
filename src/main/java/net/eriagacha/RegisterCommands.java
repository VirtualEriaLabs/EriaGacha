package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaController;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

@Log4j2
public class RegisterCommands implements Command<Object> {



  @Override
  public int run(CommandContext<Object> context) {
    return 0;
  }


  /**
   * load the dependencies
   */
  public static void init() {
    GachaController.loadTheGacha();
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
            GachaController.giveItem(context);
            return 1;
          }));
    });

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("obtenerGacha")
          .executes(context -> {

            //String texto = gr.selectGachaTelemetry(context.getSource().getPlayer().getName().asString());

            //context.getSource().sendFeedback(new LiteralText(), false);
            return 1;

          }));
    });

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("commision")
          .executes(context -> {
            try {
              Commissions.getCommision(context);
            } catch (IOException e) {
              e.printStackTrace();
            }
            return 1;
          }));
    });
  }

  //TODO : INVESTIGATE USERS ARGUMENTS READING
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



