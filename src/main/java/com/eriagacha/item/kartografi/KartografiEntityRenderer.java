package com.eriagacha.item.kartografi;

import static com.eriagacha.register.RegisterItem.BASE_SCROLL_ITEM;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

public class KartografiEntityRenderer
    implements BlockEntityRenderer<KartografiEntity> {

  private static ItemStack stack = new ItemStack(BASE_SCROLL_ITEM, 1);

  public KartografiEntityRenderer(BlockEntityRendererFactory.Context ctx) {
  }

  @Override
  public void render(KartografiEntity entity, float tickDelta, MatrixStack matrices,
                     VertexConsumerProvider vertexConsumers, int light, int overlay) {
    matrices.push();
    double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
    matrices.translate(0.5, 1.25 + offset, 0.5);
    matrices.multiply(Vec3f.POSITIVE_Y
        .getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));
    int lightAbove = WorldRenderer
        .getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
    MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers,1);
    matrices.pop();
  }
}
