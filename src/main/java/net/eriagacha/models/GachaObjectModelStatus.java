package net.eriagacha.models;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class GachaObjectModelStatus extends GachaObjectModel {
  private StatusEffectInstance statusEffectInstance;

  @Override
  public void reward(ServerPlayerEntity player, ItemStack moneyCondition) throws CommandSyntaxException {
    player.setStatusEffect(this.getStatusEffectInstance(), player);
    //getSource().sendFeedback(new LiteralText("Has obtenido " + this.getRewardName()), false);
    //getSource().sendFeedback(new LiteralText("Has obtenido " + this.getRewardName()), false);

    player.getInventory()
        .removeStack(player.getInventory().getSlotWithStack(moneyCondition), 1);
  }
}
