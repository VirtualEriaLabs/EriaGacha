package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlockEntity.GACHA_BENCH_ENTITY;

import com.eriagacha.item.gachabench.GachaBenchEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class RegisterBlockEntityRenderer {

  public static void init() {
    BlockEntityRendererRegistry.register(GACHA_BENCH_ENTITY, GachaBenchEntityRenderer::new);
  }
}
