package net.eriagacha.gacha;

import java.util.List;
import net.eriagacha.api.MinecraftGivable;
import net.eriagacha.api.MinecraftRewarder;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRewarder implements MinecraftRewarder {

  @Override
  public void reward(List<ServerPlayerEntity> player, MinecraftGivable reward) {
    player.stream().forEach(serverPlayerEntity -> reward.apply(serverPlayerEntity));
  }
}
