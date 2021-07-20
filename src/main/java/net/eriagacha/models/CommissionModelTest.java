package net.eriagacha.models;


import net.minecraft.item.Item;

public class CommissionModelTest {
  private String commissionName;
  private String commissionTask;
  private Item itemNeeded;
  private int itemQuanty;


  public CommissionModelTest(String commissionName, String commissionTask, Item itemNeeded, int itemQuanty) {
    this.commissionName = commissionName;
    this.commissionTask = commissionTask;
    this.itemNeeded = itemNeeded;
    this.itemQuanty = itemQuanty;
  }

  public CommissionModelTest() {

  }


  public String getCommissionName() {
    return commissionName;
  }

  public void setCommissionName(String commissionName) {
    this.commissionName = commissionName;
  }

  public String getCommissionTask() {
    return commissionTask;
  }

  public void setCommissionTask(String commissionTask) {
    this.commissionTask = commissionTask;
  }

  public Item getItemNeeded() {
    return itemNeeded;
  }

  public void setItemNeeded(Item itemNeeded) {
    this.itemNeeded = itemNeeded;
  }

  public int getItemQuanty() {
    return itemQuanty;
  }

  public void setItemQuanty(int itemQuanty) {
    this.itemQuanty = itemQuanty;
  }
}
