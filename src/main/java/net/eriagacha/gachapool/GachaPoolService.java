package net.eriagacha.gachapool;

import java.util.ArrayList;
import java.util.List;
import net.eriagacha.GachaRoll;
import net.eriagacha.Rewarder;
import net.eriagacha.event.EventHandler;
import net.eriagacha.event.GachaRollEvent;
import net.eriagacha.utils.GachaType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

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

    return (
        serverPlayerEntity.getInventory().contains(new ItemStack(Item.byRawId(gachaRollRawId))) &&
            serverPlayerEntity.getInventory().getStack(serverPlayerEntity.getInventory()
                .getSlotWithStack(new ItemStack(Item.byRawId(gachaRollRawId)))).getCount() >=
                gachaRollItemQuantity);
  }

  public void getReward(ServerPlayerEntity serverPlayerEntity, int gachaRollRawId,
                        int gachaRollItemQuantity) {

    GachaRoll gr = GachaRoll.builder().gachaRewards(new ArrayList<>())
        .moneyCondition(new ItemStack(Item.byRawId(gachaRollRawId)))
        .rollQuantity(gachaRollItemQuantity).build();

    for (int i = 0; i < gachaRollItemQuantity; i++) {
      if (GachaType.getGachaType(gachaRollRawId) == GachaType.CHEAP_GACHA_ID) {
        gr.setGachaRewards(GachaBagRegister.CHEAP_GACHA_BAG.getRandom());
      }
      if (GachaType.getGachaType(gachaRollRawId) == GachaType.EXPENSIVE_GACHA_ID) {
        gr.setGachaRewards(GachaBagRegister.EXPENSIVE_GACHA_BAG.getRandom());
      }
    }
    List<ServerPlayerEntity> spe = new ArrayList<>();
    spe.add(serverPlayerEntity);
    Rewarder.reward(spe, gr);

  }
}
