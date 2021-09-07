package com.eriagacha.event;

import com.eriagacha.api.MinecraftGivable;
import net.minecraft.server.network.ServerPlayerEntity;

public interface GachaRollObserver {
  void onNotify(MinecraftGivable givable, ServerPlayerEntity spe, GachaRollEvent gachaRollEvent);
}
