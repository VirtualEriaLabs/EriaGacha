package net.eriagacha.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import net.minecraft.item.Item;


@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Log4j2

public class CommissionModelItem extends CommissionModel {

  private Item itemNeeded;
  private int itemQuanty;

  public int getItemQuanty() {
    return itemQuanty;
  }
}
