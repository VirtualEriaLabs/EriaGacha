package com.eriagacha.item.kartografi;

import static com.eriagacha.register.RegisterBlockEntity.KARTOGRAFI_ENTITY_TYPE;

import java.util.Optional;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Stainable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class KartografiBlock extends BlockWithEntity implements Stainable {
  public KartografiBlock(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                            Hand hand, BlockHitResult hit) {
    if (world.isClient) {
      return ActionResult.SUCCESS;
    }
    Optional.ofNullable(super.createScreenHandlerFactory(state, world, pos))
        .ifPresent(player::openHandledScreen);
    return ActionResult.CONSUME;
  }


  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new KartografiEntity(pos, state);
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                BlockEntityType<T> type) {
    return checkType(type, KARTOGRAFI_ENTITY_TYPE, KartografiEntity::tick);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> GameEventListener getGameEventListener(World world,
                                                                        T blockEntity) {
    return super.getGameEventListener(world, blockEntity);
  }

  @Override
  public DyeColor getColor() {
    return DyeColor.MAGENTA;
  }
}
