package com.eriagacha.mixin;

import lombok.extern.log4j.Log4j2;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Log4j2
@Mixin(OreBlock.class)
public class MainMixin {

  @Inject(at = @At("HEAD"), method = "onStacksDropped")
  public void onMined(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack,
                      CallbackInfo ci) {


  }
}
