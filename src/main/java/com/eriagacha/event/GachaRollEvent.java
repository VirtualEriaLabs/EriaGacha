package com.eriagacha.event;

import com.eriagacha.gacha.GachaRoll;
import lombok.Builder;
import net.minecraft.server.network.ServerPlayerEntity;

@Builder
public class GachaRollEvent extends EriaEvent {

  @Override
  public void notify(GachaRoll gomi, ServerPlayerEntity player) {
    for (GachaRollObserver observer : this.observer) {
      observer.onNotify(gomi, player, this);
    }
  }

}
