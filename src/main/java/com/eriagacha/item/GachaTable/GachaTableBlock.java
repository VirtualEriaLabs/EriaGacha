package com.eriagacha.item.GachaTable;

import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GachaTable extends BlockWithEntity {

  protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

  public GachaTable() {
    super(AbstractBlock.Settings.of(Material.STONE).hardness(2f));
  }

  @Override
  public boolean hasSidedTransparency(BlockState state) {
    return true;
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (world.isClient) {
      return ActionResult.SUCCESS;
    } else {
      player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
      return ActionResult.CONSUME;
    }
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new GachaTableEntity(pos,state);
  }

  @Override
  public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    super.randomDisplayTick(state, world, pos, random);

    for(int i = -2; i <= 2; ++i) {
      for(int j = -2; j <= 2; ++j) {
        if (i > -2 && i < 2 && j == -1) {
          j = 2;
        }

        if (random.nextInt(16) == 0) {
          for(int k = 0; k <= 1; ++k) {
            BlockPos blockPos = pos.add(i, k, j);
            if (world.getBlockState(blockPos).isOf(Blocks.BOOKSHELF)) {
              if (!world.isAir(pos.add(i / 2, 0, j / 2))) {
                break;
              }

              world.addParticle(ParticleTypes.ENCHANT, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + random.nextFloat()) - 0.5D, (double)((float)k - random.nextFloat() - 1.0F), (double)((float)j + random.nextFloat()) - 0.5D);
            }
          }
        }
      }
    }
  }

}

