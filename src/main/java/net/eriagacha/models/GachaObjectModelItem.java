package net.eriagacha.models;

import static net.eriagacha.utils.GachaUtils.GACHA_REQUIEREMENT;
import static net.eriagacha.utils.PlayerHelper.getPlayer;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaTelemetryController;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Log4j2
public class GachaObjectModelItem extends GachaObjectModel {
  private Item item;
  private int itemQuantity;

  @Override
  public void reward(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
    ServerPlayerEntity self = getPlayer(ctx);

    boolean hasBeenInserted =
        self.getInventory().insertStack(new ItemStack(this.getItem(), this.getItemQuantity()));
    if (!hasBeenInserted) {
      log.error("Inventory is full");
      throw new SimpleCommandExceptionType(
          new TranslatableText("Tienes el inventario lleno Puto")).create();
    }

    //GachaTelemetryModel GTM = GachaTelemetry.registerTelemetry();

    GachaTelemetryController gt = new GachaTelemetryController();

    GachaTelemetryController.InsertTelemetry(
        this.getItem().getTranslationKey(),
        ctx.getSource().getName());

    ctx.getSource().sendFeedback(new LiteralText(
        "Has obtenido " + this.getItemQuantity() + " " +
            new TranslatableText(this.getItem().getTranslationKey()).getString()), false);
    self.getInventory()
        .removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT), 1);
  }
}
