package net.eriagacha.utils;

import net.eriagacha.models.CommissionModelTest;
import net.minecraft.item.Items;

public class CommissionsUtils {

  public static CommissionModelTest getItemMission()
  {
    /*
    CommissionModelItem cm = CommissionModelItem.builder()
        .commissionName("Coge piedra")
        .commissionTask("Eso")
        .itemNeeded(Items.OAK_WOOD)
        .itemQuanty(32)
        .build();
     */
    CommissionModelTest cm = new CommissionModelTest("Coger Madera","Debes recoger x32 de madera",
        Items.OAK_WOOD,1);
    
    System.out.println("SOY UN FLEJE DE DATOS OAIDSJOSIPDJADSIJ");
    System.out.println(cm.getItemNeeded());

    return cm;
  }
}
