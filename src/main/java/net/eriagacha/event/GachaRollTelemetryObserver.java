package net.eriagacha.event;

import net.eriagacha.models.GachaRewardModel;
import net.eriagacha.telemetry.GachaTelemetryController;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRollTelemetryObserver implements GachaRollObserver {

  @Override
  public void onNotify(GachaRewardModel gmi, ServerPlayerEntity spe,
                       GachaRollEvent gachaRollEvent) {
    GachaTelemetryController
        .insertTelemetry(spe.getName().getString(), gmi.getItem().getName().getString());
  }
}
