package net.eriagacha.item;

import static net.eriagacha.register.RegisterItems.status;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class AdeptusTemptation extends Item {


  public AdeptusTemptation() {
    super(new Item.Settings().group(ItemGroup.MISC)
        .food(new FoodComponent.Builder().hunger(20).saturationModifier(40f).snack().alwaysEdible()
            .statusEffect(status(StatusEffects.SPEED, 20000, 4), 2)
            .statusEffect(status(StatusEffects.SPEED, 20000, 4), 2)
            .statusEffect(status(StatusEffects.HASTE, 20000, 8), 2)
            .statusEffect(status(StatusEffects.NIGHT_VISION, 20000, 0), 2)
            .statusEffect(status(StatusEffects.REGENERATION, 20000, 0), 2)
            .statusEffect(status(StatusEffects.ABSORPTION, 20000, 2), 2)
            .statusEffect(status(StatusEffects.DOLPHINS_GRACE, 20000, 1), 2)
            .statusEffect(status(StatusEffects.RESISTANCE, 20000, 1), 2)
            .statusEffect(status(StatusEffects.JUMP_BOOST, 20000, 3), 2)
            .statusEffect(status(StatusEffects.STRENGTH, 20000, 2), 2)
            .statusEffect(status(StatusEffects.FIRE_RESISTANCE, 20000, 0), 2)
            .statusEffect(status(StatusEffects.CONDUIT_POWER, 20000, 0), 2)
            .build()));
  }
}
