package com.eriagacha.mixin;

import com.eriagacha.register.RegisterItem;
import lombok.extern.log4j.Log4j2;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.ItemEntity;
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


    double multiplier = 1;
    var blockName = state.getBlock().asItem().getName().asString();
    log.error("Soy un {}", blockName);
    //TODO: Serialize this method
    if (blockName.equals(Blocks.DIAMOND_ORE.getName())) {
      multiplier = 2.5;
    }
    if (blockName.equals(Blocks.IRON_ORE.getName())) {
      multiplier = 1.5;
    }
    world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(),
        new ItemStack(RegisterItem.MINERAL_ESSENCE_ITEM, (int) Math.floor(Math.random() * multiplier))));
  }
}
