package net.eriagacha;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GachaObject<T>{

  String rewardName = new String();
  int itemQuanty = 1;
  Items items = new Items();
  Item item = null;
  StatusEffect statusEffect = null;
  StatusEffectInstance statusEffectInstance = null;
  //TODO CREAR ENUM PARA DEFINIR EL TIPO DE RECOMPENSA
  int rewardType = 0;
  //CONSTRUCTORES


  public GachaObject() {}
  public GachaObject(Items items, String rewardName, int rewardType) {
    this.rewardType = 1;
    this.items = items;
    this.rewardName = rewardName;
  }
  public GachaObject(Item item, String rewardName) {
    this.rewardType = 1;
    this.item = item;
    this.rewardName = rewardName;
  }

  public GachaObject(Item item, String rewardName, int itemQuanty) {
    this.rewardType = 1;
    this.item = item;
    this.rewardName = rewardName;
    this.itemQuanty = itemQuanty;
  }

  public GachaObject(StatusEffectInstance statusEffectInstance, String rewardName) {
    this.rewardType = 2;
    this.rewardName = rewardName;
    this.statusEffectInstance = statusEffectInstance;
  }


  //SETTERS & GETTERS
  public void setItem(Item item){this.item = item;}

  public Item getItem(){return this.item;}

  public void setItems(Items item){this.items = item;}

  public Items getItems(){return this.items;}

}
