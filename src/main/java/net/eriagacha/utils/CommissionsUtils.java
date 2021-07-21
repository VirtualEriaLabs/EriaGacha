package net.eriagacha.utils;

import net.eriagacha.models.CommissionModel;
import net.eriagacha.models.CommissionModelItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class CommissionsUtils {

  public static CommissionModel getItemMission()
  {
    CommissionModel cm = CommissionModelItem.builder()
        .commissionName("Coge piedra")
        .commissionTask("Eso")
        .itemNeeded(Item.getRawId(Items.OAK_WOOD))
        .itemQuanty(32)
        .build();

    return cm;
  }
}
