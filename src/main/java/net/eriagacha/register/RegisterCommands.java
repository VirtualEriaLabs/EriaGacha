package net.eriagacha.register;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.gacha.GachaBagRegister;
import net.eriagacha.gacha.GachaPoolService;
import net.eriagacha.gacha.GachaPoolServiceFactory;
import net.eriagacha.telemetry.GachaTelemetryController;
import net.eriagacha.utils.NameSpaces;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

@Log4j2
public class RegisterCommands implements Command<Object> {

  public static void init() {
    GachaBagRegister.registerItems();
    registerCommands();
  }

  public static void registerCommands() {

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_cheap_single_roll")
          .executes(context -> {
            giveReward(context,
                Item.getRawId(NameSpaces.GachaItems.CHEAP_GACHA_REQUIEREMENT.getItem()), 1);
            return 1;
          }));
    });

    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
      dispatcher.register(CommandManager.literal("gacha_expensive_single_roll")
          .executes(context -> {
            giveReward(context,
                Item.getRawId(NameSpaces.GachaItems.EXPENSIVE_GACHA_REQUIEREMENT.getItem()),
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
    assert gachaPoolService != null : "NullPointerException";
    gachaPoolService.rollGacha(player,
        new ItemStack(Item.byRawId(clientMoneyConditionRawId), clientMoneyQuantity));
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



