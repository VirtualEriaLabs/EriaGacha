package com.eriagacha.api;

import java.util.List;
import net.minecraft.server.network.ServerPlayerEntity;

@FunctionalInterface
public interface MinecraftRewarder {
  void reward(List<ServerPlayerEntity> player, MinecraftGivable reward);
}
