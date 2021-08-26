package net.eriagacha.services.gachapool;

import net.eriagacha.EventHandler;
import net.eriagacha.GachaRollEvent;
import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.models.GachaObjectModelItem;
import net.eriagacha.models.GachaObjectModelStatus;
import net.eriagacha.utils.WeightedRandomBag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

public class GachaPoolService {

  GachaRollEvent gachaRollEvent;
  WeightedRandomBag weightedRandomBag;
  ItemStack rollCost;

  public GachaPoolService(WeightedRandomBag weightedRandomBag, ItemStack rollCost) {
    this.weightedRandomBag = weightedRandomBag;
    this.rollCost = rollCost;
    this.gachaRollEvent = EventHandler.gre;
  }


  public boolean conditionsMet() {
    //TODO : Condiciones
    return true;
  }

  public GachaObjectModel getReward(ServerPlayerEntity serverPlayerEntity, int gachaRollRawId,
                                    int gachaRollItemQuantity) {

    serverPlayerEntity.getInventory().removeStack(serverPlayerEntity.getInventory()
        .getSlotWithStack(new ItemStack(Item.byRawId(gachaRollRawId))), gachaRollItemQuantity);

    var gachaObjectModel = weightedRandomBag.getRandom();
    if (gachaObjectModel instanceof GachaObjectModelItem) {

      var gachaModelItem = (GachaObjectModelItem) gachaObjectModel;
      serverPlayerEntity.getInventory().insertStack(
          new ItemStack(gachaModelItem.getItem(), gachaModelItem.getItemQuantity()));

      serverPlayerEntity.sendMessage(new TranslatableText("text.eriagacha.obtained_translated",
              new TranslatableText(gachaModelItem.getItem().getTranslationKey())),
          false);

      this.gachaRollEvent.notify(gachaModelItem, serverPlayerEntity);

    } else if (gachaObjectModel instanceof GachaObjectModelStatus) {
      var gachaModelStatus = (GachaObjectModelStatus) gachaObjectModel;
      serverPlayerEntity
          .setStatusEffect(gachaModelStatus.getStatusEffectInstance(), serverPlayerEntity);
    }

    return gachaObjectModel;
  }
}
