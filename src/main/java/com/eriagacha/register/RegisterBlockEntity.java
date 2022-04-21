package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlock.KARTOGRAFI;
import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.kartografi.KartografiEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class RegisterBlockEntity {
  public static BlockEntityType<KartografiEntity> KARTOGRAFI_ENTITY_TYPE;

  public static void init(){
    KARTOGRAFI_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, id("kartografi_entity"),
        FabricBlockEntityTypeBuilder.create(KartografiEntity::new, KARTOGRAFI).build(null));
  }
}
