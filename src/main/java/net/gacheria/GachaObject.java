package net.gacheria;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GachaObject<T>{

  Items items = new Items();
  Item item = null;
  public GachaObject() {}
  public GachaObject(Items items) {
    this.items = items;
  }
  public GachaObject(Item item) {
    this.item = item;
  }


  public void setItem(Item item){
    this.item = item;
  }

  public Item getItem(){
    return this.item;
  }

  public void setItems(Items item){
    this.items = item;
  }

  public Items getItems(){
    return this.items;
  }

}
