package net.eriagacha.services.GachaPool;

import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.server.network.ServerPlayerEntity;

public interface GachaPoolService {

  WeightedRandomBag<?> getWeightedRandomBag();
  boolean conditionsMet();
  GachaObjectModel getReward(ServerPlayerEntity serverPlayerEntity, int gachaRollRawId, int gachaRollItemQuantity);

}
