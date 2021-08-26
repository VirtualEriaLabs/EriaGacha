package net.eriagacha;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaTelemetryController;
import net.eriagacha.services.gachapool.GachaPoolService;
import net.eriagacha.services.gachapool.GachaPoolServiceFactory;
import net.eriagacha.utils.GachaUtils;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

@Log4j2
public class RegisterCommands implements Command<Object> {


  /**
   * Load the dependencies
   */
  public static void init() {
    GachaBagRegister.registerItems();
    registerCommands();
  }

  /**
   * Metodo para registrar los comandos
   */
  public static void registerCommands() {

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_cheap_single_roll")
          .executes(context -> {
            giveReward(context, Item.getRawId(GachaUtils.CHEAP_GACHA_REQUIEREMENT.getItem()), 1);
            return 1;
          }));
    });
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_expensive_single_roll")
          .executes(context -> {
            giveReward(context, Item.getRawId(GachaUtils.EXPENSIVE_GACHA_REQUIEREMENT.getItem()),
                1);
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


  public static void giveReward(CommandContext<ServerCommandSource> ctx,
                                int clientMoneyConditionRawId, int clientMoneyQuantity)
      throws CommandSyntaxException {

    var player = ctx.getSource().getPlayer();
    GachaPoolService gachaPoolService =
        GachaPoolServiceFactory.getInstance(clientMoneyConditionRawId);
    if (gachaPoolService.conditionsMet()) {
      gachaPoolService.getReward(player, clientMoneyConditionRawId, clientMoneyQuantity);
    } else {
      player.sendMessage(new LiteralText("Conditions to use the gacha not met"), false);
    }
  }

  public static void gachaRecord(CommandContext<ServerCommandSource> ctx)
      throws CommandSyntaxException {
    GachaTelemetryController.selectTelemetry(ctx.getSource().getPlayer());
  }

  @Override
  public int run(CommandContext<Object> context) {
    return 0;
  }

}



