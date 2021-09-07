package com.eriagacha.event;

import com.eriagacha.api.MinecraftGivable;
import com.eriagacha.giveable.GivableItemStack;
import com.eriagacha.telemetry.GachaTelemetryController;
import net.minecraft.server.network.ServerPlayerEntity;

public class GachaRollTelemetryObserver implements GachaRollObserver {

  @Override
  public void onNotify(MinecraftGivable givable, ServerPlayerEntity spe,
                       GachaRollEvent gachaRollEvent) {
    if (givable instanceof GivableItemStack) {
      GachaTelemetryController
          .insertTelemetry(spe.getName().getString(),
              ((GivableItemStack) givable).getItemStack().getItem().getName().getString());
    }
  }
}
