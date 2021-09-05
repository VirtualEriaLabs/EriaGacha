package net.eriagacha.event;

import net.eriagacha.api.MinecraftGivable;
import net.minecraft.server.network.ServerPlayerEntity;

public interface GachaRollObserver {
  void onNotify(MinecraftGivable givable, ServerPlayerEntity spe, GachaRollEvent gachaRollEvent);
}
