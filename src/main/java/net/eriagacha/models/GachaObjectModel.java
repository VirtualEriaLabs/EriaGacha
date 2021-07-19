package net.eriagacha.models;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class GachaObjectModel<T>{

  private String rewardName = new String();
  private int itemQuanty = 1;
  private Items items = new Items();
  private Item item = null;
  private StatusEffect statusEffect = null;
  private StatusEffectInstance statusEffectInstance = null;
  //TODO CREAR ENUM PARA DEFINIR EL TIPO DE RECOMPENSA
  private int rewardType = 0;

  //CONSTRUCTORES


  public GachaObjectModel() {}
  public GachaObjectModel(Items items, String rewardName, int rewardType) {
    this.rewardType = 1;
    this.items = items;
    this.rewardName = rewardName;
  }
  public GachaObjectModel(Item item, String rewardName) {
    this.rewardType = 1;
    this.item = item;
    this.rewardName = rewardName;
  }

  public GachaObjectModel(Item item, String rewardName, int itemQuanty) {
    this.rewardType = 1;
    this.item = item;
    this.rewardName = rewardName;
    this.itemQuanty = itemQuanty;
  }

  public GachaObjectModel(StatusEffectInstance statusEffectInstance, String rewardName) {
    this.rewardType = 2;
    this.rewardName = rewardName;
    this.statusEffectInstance = statusEffectInstance;
  }

  public GachaObjectModel(Item item,  int itemQuanty) {
    this.rewardType = 1;
    this.item = item;
    this.rewardName = rewardName;
    this.itemQuanty = itemQuanty;
  }

  //SETTERS & GETTERS
  public void setItem(Item item){this.item = item;}

  public Item getItem(){return this.item;}

  public void setItems(Items item){this.items = item;}

  public Items getItems(){return this.items;}

  public String getRewardName() {
    return rewardName;
  }

  public void setRewardName(String rewardName) {
    this.rewardName = rewardName;
  }

  public int getItemQuanty() {
    return itemQuanty;
  }

  public void setItemQuanty(int itemQuanty) {
    this.itemQuanty = itemQuanty;
  }

  public StatusEffect getStatusEffect() {
    return statusEffect;
  }

  public void setStatusEffect(StatusEffect statusEffect) {
    this.statusEffect = statusEffect;
  }

  public StatusEffectInstance getStatusEffectInstance() {
    return statusEffectInstance;
  }

  public void setStatusEffectInstance(
      StatusEffectInstance statusEffectInstance) {
    this.statusEffectInstance = statusEffectInstance;
  }

  public int getRewardType() {
    return rewardType;
  }

  public void setRewardType(int rewardType) {
    this.rewardType = rewardType;
  }

}
