package net.eriagacha.event;

import java.util.List;
import lombok.NonNull;
import net.eriagacha.gacha.GachaRoll;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class EriaEvent {

  @NonNull
  List<GachaRollObserver> observer;

  public void addObserver(GachaRollObserver gro) {
    this.observer.add(gro);
  }

  public void removeObserver(GachaRollObserver gro) {
    this.observer.remove(gro);
  }

  public abstract void notify(GachaRoll gachaRoll, ServerPlayerEntity player);
}
