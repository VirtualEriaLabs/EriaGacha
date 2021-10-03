package com.eriagacha.utils;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class ParticleUtils {


  public static void soulFlameArea(BlockPos pos, MinecraftClient player)
  {
    for (double z = pos.getZ() + 5; z > pos.getZ() - 5; z--) {
      for (double x = (pos.getX() + 5); x > pos.getX() - 5; x--) {
        for (double y = (pos.getY() + 5); y > pos.getY() - 5; y--) {
          BlockState blockstate = player.world.getBlockState(new BlockPos(x, y, z));
          BlockState upperBlock = player.world.getBlockState(new BlockPos(x, y + 1, z));
          if (!blockstate.isAir() && upperBlock.isAir())
            MinecraftClient.getInstance().particleManager.addParticle(
                ParticleTypes.SOUL_FIRE_FLAME, x+ 0.1f, y + 1.5, z+ 0.1f,
                0.0D, 0.00, 0.0D
            );
        }
      }
    }
  }

  public static void soulFlameArea(BlockPos pos, PlayerEntity player) throws InterruptedException {
    //player.playSound();
    for (double z = pos.getZ() + 5; z > pos.getZ() - 5; z--) {
      for (double x = (pos.getX() + 5); x > pos.getX() - 5; x--) {
        for (double y = (pos.getY() + 1); y > pos.getY() - 1; y--) {
          BlockState blockstate = player.world.getBlockState(new BlockPos(x, y, z));
          BlockState upperBlock = player.world.getBlockState(new BlockPos(x, y + 1, z));
          if (!blockstate.isAir() && upperBlock.isAir())
            MinecraftClient.getInstance().particleManager.addParticle(
                ParticleTypes.CAMPFIRE_COSY_SMOKE, x+ 0.1f, y + 1.5, z+ 0.1f,
                0.0D, 0.05, 0.0D);
        }
      }
    }
  }
}
