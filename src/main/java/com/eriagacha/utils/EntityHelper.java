package com.eriagacha.utils;

import static com.eriagacha.register.RegisterSound.HIT_ENTITY_SOUND;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;

public class EntityHelper {

  public static void OnEntityHitDamage(List<Entity> listEntities, PlayerEntity user, float damage, boolean setOnFire)
  {
    listEntities.stream().forEach(entity -> {
      user.world.playSoundFromEntity(null,entity, HIT_ENTITY_SOUND, SoundCategory.PLAYERS,100,100);
      if(setOnFire) {
        entity.setOnFireFor(5);
      }
      entity.damage(DamageSource.explosion(user), damage);
    });
  }
}
