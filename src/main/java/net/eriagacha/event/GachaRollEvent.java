package net.eriagacha.event;

import java.util.ArrayList;
import java.util.List;
import net.eriagacha.models.GachaObjectModelItem;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRollEvent {
  public List<GachaRollObserver> gros = new ArrayList<>();

  public void addObserver(GachaRollObserver gro) {
    gros.add(gro);
  }
  public void removeObserver(GachaRollObserver gro){
    gros.remove(gro);
  }
  public void notify(GachaObjectModelItem gomi, ServerPlayerEntity player){
    for (GachaRollObserver gro:gros) {
      gro.onNotify(gomi,player,this);
    }
  }

}
