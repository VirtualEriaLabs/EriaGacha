package net.eriagacha.services.GachaPool;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.eriagacha.controller.GachaTelemetryController;
import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.models.GachaObjectModelItem;
import net.eriagacha.models.GachaObjectModelStatus;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

@RequiredArgsConstructor
public class ExpensiveGachaPoolService implements GachaPoolService {

  @NonNull
  private final WeightedRandomBag weightedRandomBag;

  @Override
  public WeightedRandomBag<?> getWeightedRandomBag() {
    return null;
  }

  @Override
  public boolean conditionsMet() {

    return true;
  }

  @Override
  public GachaObjectModel getReward(ServerPlayerEntity serverPlayerEntity, int gachaRollRawId,
                                    int gachaRollItemQuantity) {
    serverPlayerEntity.getInventory().removeStack(serverPlayerEntity.getInventory()
        .getSlotWithStack(new ItemStack(Item.byRawId(gachaRollRawId))), 1);

    var gachaObjectModel = weightedRandomBag.getRandom();
    if (gachaObjectModel instanceof GachaObjectModelItem) {
      var gachaModelItem = (GachaObjectModelItem) gachaObjectModel;
      serverPlayerEntity.getInventory().insertStack(
          new ItemStack(gachaModelItem.getItem(), gachaModelItem.getItemQuantity()));
      serverPlayerEntity.sendMessage(new TranslatableText("text.eriagacha.obtained_translated",
              new Object[] {new TranslatableText(gachaModelItem.getItem().getTranslationKey())}),
          false);
      GachaTelemetryController.InsertTelemetry(
          serverPlayerEntity.getName().getString(),
          gachaModelItem.getItem().getTranslationKey()
      );

    } else if (gachaObjectModel instanceof GachaObjectModelStatus) {
      var gachaModelStatus = (GachaObjectModelStatus) gachaObjectModel;
      serverPlayerEntity
          .setStatusEffect(gachaModelStatus.getStatusEffectInstance(), serverPlayerEntity);
    }

    return gachaObjectModel;
  }
}
