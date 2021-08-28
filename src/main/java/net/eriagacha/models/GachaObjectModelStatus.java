package net.eriagacha.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class GachaObjectModelStatus extends GachaObjectModel {
  private StatusEffectInstance statusEffectInstance;

  //TODO : REDO ALL THE GACHA REGISTER LOGIC
  //Under comment saved for future refactor
  /*
  @Override
  public String reward(ServerPlayerEntity player, ItemStack moneyCondition) {
    player.setStatusEffect(this.getStatusEffectInstance(), player);
    //getSource().sendFeedback(new LiteralText("Has obtenido " + this.getRewardName()), false);
    //getSource().sendFeedback(new LiteralText("Has obtenido " + this.getRewardName()), false);

    player.getInventory()
        .removeStack(player.getInventory().getSlotWithStack(moneyCondition), 1);
    return null;
  }

   */
}
