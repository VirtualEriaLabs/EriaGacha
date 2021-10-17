package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlockEntity.KARTOGRAFI_ENTITY_TYPE;

import com.eriagacha.item.kartografi.KartografiEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class RegisterBlockEntityRenderer {

  public static void init() {
    BlockEntityRendererRegistry.register(KARTOGRAFI_ENTITY_TYPE, KartografiEntityRenderer::new);
  }
}
