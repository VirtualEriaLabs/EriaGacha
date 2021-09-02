package net.eriagacha.event;

import java.util.ArrayList;
import java.util.List;
import net.eriagacha.models.GachaRewardModel;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRollEvent {
  public List<GachaRollObserver> gros = new ArrayList<>();

  public void addObserver(GachaRollObserver gro) {
    this.gros.add(gro);
  }

  public void removeObserver(GachaRollObserver gro) {
    this.gros.remove(gro);
  }

  public void notify(GachaRewardModel gomi, ServerPlayerEntity player) {
    for (GachaRollObserver gro : this.gros) {
      gro.onNotify(gomi, player, this);
    }
  }

}
