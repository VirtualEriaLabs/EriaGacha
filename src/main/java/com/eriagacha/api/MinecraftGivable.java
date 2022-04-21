package com.eriagacha.api;

import java.util.function.Function;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public interface MinecraftGivable extends Function<ServerPlayerEntity, MinecraftGivable> {
  String name = "Givable";

  @Override
  MinecraftGivable apply(ServerPlayerEntity spe);

  default void successFeedback(ServerPlayerEntity target, Text text) {
    target.sendMessage(text, false);
  }

  default void errorFeedback(ServerPlayerEntity target) {
    target.sendMessage(new TranslatableText("text.eriagacha.roll.error"), false);
  }
}
