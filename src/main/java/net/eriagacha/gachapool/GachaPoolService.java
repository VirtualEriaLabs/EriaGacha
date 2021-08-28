package net.eriagacha.gachapool;

import net.eriagacha.event.EventHandler;
import net.eriagacha.event.GachaRollEvent;
import net.eriagacha.models.GachaObjectModel;
import net.eriagacha.models.GachaObjectModelItem;
import net.eriagacha.models.GachaObjectModelStatus;
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


  public boolean conditionsMet(ServerPlayerEntity serverPlayerEntity, int gachaRollRawId,
                               int gachaRollItemQuantity) {
    //TODO : Condiciones
    if (serverPlayerEntity.getInventory().contains(new ItemStack(Item.byRawId(gachaRollRawId)))) {
      if (serverPlayerEntity.getInventory().getStack(serverPlayerEntity.getInventory()
          .getSlotWithStack(new ItemStack(Item.byRawId(gachaRollRawId)))).getCount() >=
          gachaRollItemQuantity) {
          return true;
      }
    }

    return false;
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
