package net.eriagacha.event;

import net.eriagacha.models.GachaObjectModelItem;
import net.minecraft.server.network.ServerPlayerEntity;

public interface GachaRollObserver {

  void onNotify(GachaObjectModelItem gmi, ServerPlayerEntity spe, GachaRollEvent gachaRollEvent);
}
