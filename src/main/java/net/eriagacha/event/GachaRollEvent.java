package net.eriagacha.event;

import lombok.Builder;
import net.eriagacha.gacha.GachaRoll;
import net.minecraft.server.network.ServerPlayerEntity;

@Builder
public class GachaRollEvent extends EriaEvent {

  public void notify(GachaRoll gomi, ServerPlayerEntity player) {
    for (GachaRollObserver observer : this.observer) {
      observer.onNotify(gomi, player, this);
    }
  }

}
