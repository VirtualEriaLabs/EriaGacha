package net.eriagacha.models;

import static net.eriagacha.utils.GachaUtils.GACHA_REQUIEREMENT;
import static net.eriagacha.utils.PlayerHelper.getPlayer;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class GachaObjectModelStatus extends GachaObjectModel {
  private StatusEffectInstance statusEffectInstance;

  @Override
  public void reward(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
    ServerPlayerEntity self = getPlayer(ctx);
    self.setStatusEffect(this.getStatusEffectInstance(), self);
    ctx.getSource().sendFeedback(new LiteralText("Has obtenido " + this.getRewardName()), false);
    self.getInventory()
        .removeStack(self.getInventory().getSlotWithStack(GACHA_REQUIEREMENT), 1);
  }
}
