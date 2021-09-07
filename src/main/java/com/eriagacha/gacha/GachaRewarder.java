package com.eriagacha.gacha;

import com.eriagacha.api.MinecraftGivable;
import com.eriagacha.api.MinecraftRewarder;
import java.util.List;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRewarder implements MinecraftRewarder {

  @Override
  public void reward(List<ServerPlayerEntity> player, MinecraftGivable reward) {
    player.stream().forEach(serverPlayerEntity -> reward.apply(serverPlayerEntity));
  }
}
