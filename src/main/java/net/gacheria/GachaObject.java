package net.gacheria;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GachaObject<T>{

  String rewardName = new String();
  int itemQuanty = 1;
  Items items = new Items();
  Item item = null;
  public GachaObject() {}

  public GachaObject(Items items, String rewardName) {
    this.items = items;
    this.rewardName = rewardName;
  }

  public GachaObject(Item item, String rewardName) {
    this.item = item;
    this.rewardName = rewardName;
  }

  public GachaObject(Item item, String rewardName, int itemQuanty) {
    this.item = item;
    this.rewardName = rewardName;
    this.itemQuanty = itemQuanty;
  }


  public void setItem(Item item){this.item = item;}

  public Item getItem(){return this.item;}

  public void setItems(Items item){this.items = item;}

  public Items getItems(){return this.items;}

}
