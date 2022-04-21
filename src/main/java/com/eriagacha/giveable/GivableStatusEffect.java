package com.eriagacha.giveable;

import com.eriagacha.api.MinecraftGivable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

@Builder
@Getter
@EqualsAndHashCode
public class GivableStatusEffect implements MinecraftGivable {

  @NonNull
  final String name;
  @NonNull
  final StatusEffectInstance statusEffectInstance;

  @Override
  public MinecraftGivable apply(ServerPlayerEntity spe) {
    if (this.statusEffectInstance == null) {
      this.errorFeedback(spe);
      return this;
    }
    spe.setStatusEffect(this.statusEffectInstance, spe);
    this.successFeedback(spe, new TranslatableText(""));
    return this;
  }
}
