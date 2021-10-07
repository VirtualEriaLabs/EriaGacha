package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlock.GACHA_BENCH;
import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.gachabench.GachaBenchEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class RegisterBlockEntity {
  public static BlockEntityType<GachaBenchEntity> GACHA_BENCH_ENTITY;

  public static void init(){
    GACHA_BENCH_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, id("gacha_bench_entity"),
        FabricBlockEntityTypeBuilder.create(GachaBenchEntity::new, GACHA_BENCH).build(null));
  }
}
