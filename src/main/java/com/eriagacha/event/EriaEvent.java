package com.eriagacha.event;

import com.eriagacha.gacha.GachaRoll;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class EriaEvent {

  @NonNull
  List<GachaRollObserver> observer = new ArrayList<>();

  public void addObserver(GachaRollObserver gro) {
    if (this.observer == null) {
      this.observer = new ArrayList<>();
    }
    this.observer.add(gro);
  }

  public void removeObserver(GachaRollObserver gro) {
    this.observer.remove(gro);
  }

  public abstract void notify(GachaRoll gachaRoll, ServerPlayerEntity player);
}
