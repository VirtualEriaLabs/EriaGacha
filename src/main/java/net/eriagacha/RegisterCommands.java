package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaController;
import net.eriagacha.controller.GachaTelemetryController;
import net.eriagacha.utils.GachaUtils;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

@Log4j2
public class RegisterCommands implements Command<Object> {


  /**
   * Load the dependencies
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
      dispatcher.register(CommandManager.literal("gacha_cheap_roll")
          .executes(context -> {
            giveReward(context, GachaUtils.CHEAP_GACHA_REQUIEREMENT);
            return 1;
          }));
    });
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_expensive_roll")
          .executes(context -> {
            giveReward(context, GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT);
            return 1;
          }));
    });

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_record")
          .executes(context -> {
            gachaRecord(context);
            return 1;
          }));
    });
  }


  public static void giveReward(CommandContext<ServerCommandSource> ctx, ItemStack moneyCondition) throws CommandSyntaxException {
    GachaController.giveGachaReward(ctx.getSource().getPlayer(), moneyCondition);
  }

  public static void gachaRecord(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
    GachaTelemetryController.selectTelemetry(ctx);
  }

  @Override
  public int run(CommandContext<Object> context) {return 0;}

}



