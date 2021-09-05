package net.eriagacha.giveable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import net.eriagacha.api.MinecraftGivable;
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
    if (statusEffectInstance == null) {
      this.errorFeedback(spe);
      return this;
    }
    spe.setStatusEffect(this.statusEffectInstance, spe);
    this.successFeedback(spe, new TranslatableText(""));
    return this;
  }
}
