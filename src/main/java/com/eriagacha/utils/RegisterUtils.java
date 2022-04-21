package com.eriagacha.utils;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;

public class RegisterUtils {

  public static Identifier id(String identifierName) {
    return new Identifier(NameSpaces.PROJECT_NAME, identifierName);
  }

  public static StatusEffectInstance status(StatusEffect status, int dur, int amp) {
    return new StatusEffectInstance(status, dur, amp);
  }
}
