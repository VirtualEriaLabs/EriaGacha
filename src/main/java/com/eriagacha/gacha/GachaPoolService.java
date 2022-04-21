package com.eriagacha.gacha;

import com.eriagacha.api.MinecraftRewarder;
import com.eriagacha.event.EriaEvent;
import java.util.Arrays;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

@Getter
@EqualsAndHashCode
@Builder
public class GachaPoolService {

  EriaEvent gachaRollEvent;
  WeightedRandomBag weightedRandomBag;
  ItemStack rollCost;
  MinecraftRewarder minecraftRewarder;


  private boolean conditionsMet(ServerPlayerEntity player, ItemStack itemStack) {

    int slot = player
        .getInventory()
        .getSlotWithStack(itemStack);

    return player.getInventory().contains(itemStack) &&
        player.getInventory().getStack(slot).getCount() >= this.rollCost.getCount();
  }

  public void rollGacha(ServerPlayerEntity player, ItemStack itemStack) {
    if (this.conditionsMet(player, itemStack)) {
      this.rollGacha(player);
      return;
    }
    player.sendMessage(new LiteralText("Conditions to use the gacha not met"), false);
  }

  public void rollGacha(ServerPlayerEntity player) {
    player.getInventory()
        .removeStack(player.getInventory().getSlotWithStack(this.rollCost), this.rollCost.getCount());
    this.minecraftRewarder.reward(Arrays.asList(player), this.weightedRandomBag.getRandom());
    player.sendMessage(new LiteralText("You rolled a Gacha!!"), false);
  }
}
