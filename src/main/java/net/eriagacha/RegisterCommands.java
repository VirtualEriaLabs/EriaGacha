package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaController;
import net.eriagacha.controller.GachaTelemetryController;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

@Log4j2
public class RegisterCommands implements Command<Object> {


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
  public static void registerCommands() {

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
            GachaTelemetryController.selectTelemetry(context);
            return 1;
          }));
    });
  }

  @Override
  public int run(CommandContext<Object> context) {
    return 0;
  }


}



