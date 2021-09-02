package net.eriagacha.event;

import net.eriagacha.models.GachaRewardModel;
import net.minecraft.server.network.ServerPlayerEntity;

public interface GachaRollObserver {

  void onNotify(GachaRewardModel gmi, ServerPlayerEntity spe, GachaRollEvent gachaRollEvent);
}
