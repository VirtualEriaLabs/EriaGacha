package com.eriagacha.utils;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class ParticleUtils {


  public static void soulFlameArea(BlockPos centerPos, MinecraftClient player, int radio, int farFromPlayer)
  {
    var blockPosList = BlockPosUtils.cubicBlockArea(radio,farFromPlayer, centerPos);
    for (BlockPos pos : blockPosList)
    {
      var x = pos.getX();
      var y = pos.getY();
      var z = pos.getZ();
      BlockState blockstate = player.world.getBlockState(new BlockPos(x,y ,z ));
      BlockState upperBlock = player.world.getBlockState(new BlockPos(x, y + 1, z));
      if (!blockstate.isAir() && upperBlock.isAir())
      {
        MinecraftClient.getInstance().particleManager.addParticle(
            ParticleTypes.SOUL_FIRE_FLAME, x+ 0.1f, y + 1.5, z+ 0.1f,
            0.0D, 0.05, 0.0D );
        MinecraftClient.getInstance().particleManager.addParticle(
            ParticleTypes.SMOKE, x+ 0.1f, y + 1.5, z+ 0.1f,
            Math.random()*0.1, 0.2, Math.random()*0.1);
      }
    }
  }
}
