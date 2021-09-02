package net.eriagacha;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

public class Rewarder {

  public static void reward(List<ServerPlayerEntity> spe, GachaRoll gr) {
    for (var reward : gr.getGachaRewards()) {
      for (var player : spe) {
        player.getInventory()
            .removeStack(player.getInventory()
                .getSlotWithStack(gr.getMoneyCondition()), 1);
        if (reward.item != null) {
          player.getInventory()
              .insertStack(new ItemStack(reward.getItem(), reward.getItemQuantity()));
          player.sendMessage(new TranslatableText("text.eriagacha.obtained_translated",
              new TranslatableText(reward.getItem().getTranslationKey())), false);
        } else if (reward.statusEffectInstance != null) {
          player.setStatusEffect(reward.getStatusEffectInstance(), player);
          player.sendMessage(new TranslatableText("TOTRANSLATESTRING: I'm a buff"), false);
        }
      }
    }
  }
}
