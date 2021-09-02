package net.eriagacha.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class GachaRewardModel<T> {
  public StatusEffectInstance statusEffectInstance;
  public Item item;
  public int itemQuantity;
  public String rewardName;
  public double weight;


  public T apply() {
    return null;
  }
}
