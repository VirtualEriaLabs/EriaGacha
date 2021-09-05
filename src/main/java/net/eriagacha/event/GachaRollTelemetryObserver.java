package net.eriagacha.event;

import net.eriagacha.api.MinecraftGivable;
import net.eriagacha.giveable.GivableItemStack;
import net.eriagacha.telemetry.GachaTelemetryController;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRollTelemetryObserver implements GachaRollObserver {

  @Override
  public void onNotify(MinecraftGivable givable, ServerPlayerEntity spe,
                       GachaRollEvent gachaRollEvent) {
    if(givable instanceof GivableItemStack) {
        GachaTelemetryController
        .insertTelemetry(spe.getName().getString(),
            ((GivableItemStack) givable).getItemStack().getItem().getName().getString());
    }
  }
}
