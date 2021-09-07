package com.eriagacha.gacha;

import com.eriagacha.api.MinecraftGivable;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

@Getter
@EqualsAndHashCode
@Builder
public class GachaRoll implements MinecraftGivable {

  private List<MinecraftGivable> rewards;
  private String name;
  private double weight;
  private TranslatableText rollMessage;

  @Override
  public MinecraftGivable apply(ServerPlayerEntity player) {
    if (this.rewards == null) {
      this.errorFeedback(player);
      return this;
    }
    this.rewards.stream().forEach(reward -> reward.apply(player));
    this.successFeedback(player, this.rollMessage);
    return this;
  }
}
