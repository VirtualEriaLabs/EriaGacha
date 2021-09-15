package com.eriagacha.item.GachaTable;

import java.util.function.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class GachaTableAbstractBlock<E extends BlockEntity> extends BlockWithEntity {

  protected final Supplier<BlockEntityType<? extends E>> entityTypeRetriever;

  protected GachaTableAbstractBlock(AbstractBlock.Settings settings, Supplier<BlockEntityType<? extends E>> entityTypeSupplier) {
    super(settings);
    this.entityTypeRetriever = entityTypeSupplier;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return null;
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                BlockEntityType<T> type) {
    return super.getTicker(world, state, type);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> GameEventListener getGameEventListener(World world,
                                                                        T blockEntity) {
    return super.getGameEventListener(world, blockEntity);
  }
}
