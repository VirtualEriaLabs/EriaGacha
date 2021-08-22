package net.eriagacha.models;

import static net.eriagacha.utils.PlayerHelper.getPlayer;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.controller.GachaTelemetryController;
import net.eriagacha.utils.GachaUtils;
import net.eriagacha.utils.TextUtils;
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
      throw new SimpleCommandExceptionType(
          new TranslatableText("inventory.full")).create();
    }

    GachaTelemetryController.InsertTelemetry(
        ctx.getSource().getName(),
        this.getItem().getTranslationKey()
    );


    ctx.getSource().sendFeedback(new LiteralText(
        String.format("%s%d %s",
            TextUtils.translatedTextToString("text.eriagacha.obtained"),
            this.getItemQuantity(),
            TextUtils.translatedTextToString(this.getItem().getTranslationKey())
        )), false);

    self.getInventory()
        .removeStack(self.getInventory().getSlotWithStack(GachaUtils.CHEAP_GACHA_REQUIEREMENT), 1);
  }
}
