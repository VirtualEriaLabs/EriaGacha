package com.eriagacha.item.GachaFurnace;

import com.eriagacha.register.RegisterItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GachaFurnaceBlock extends GachaFurnaceAbstractBlock<GachaFurnaceEntity> {

  protected static final VoxelShape SHAPE =
      Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

  public GachaFurnaceBlock() {
    super(Settings.of(Material.STONE).strength(1F).sounds(BlockSoundGroup.ANVIL),
        () -> RegisterItems.GACHA_FURNACE_ENTITY);
    this.setDefaultState(this.stateManager.getDefaultState());
  }

  @Override
  public boolean hasSidedTransparency(BlockState state) {
    return true;
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos,
                                    ShapeContext context) {
    return SHAPE;
  }


  /*
  @Override
  public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack)
  {

    if (blockEntity != null) {
      DefaultedList<ItemStack> items = DefaultedList.ofSize(35, ItemStack.EMPTY);
      Inventories.readNbt(blockEntity.toInitialChunkDataNbt(), items);
      for (var item : items)
      {
        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), item));
      }
    }
  }
*/
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }


  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState,
                              boolean moved) {
    if (!state.isOf(newState.getBlock())) {

      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof Inventory) {
        ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
        world.updateComparators(pos, this);
      }

      super.onStateReplaced(state, world, pos, newState, moved);
    }
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                            Hand hand, BlockHitResult hit) {
    if (world.isClient) {
      return ActionResult.SUCCESS;
    } else {
      NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
      if (namedScreenHandlerFactory != null) {
        player.openHandledScreen(namedScreenHandlerFactory);
      }
      return ActionResult.CONSUME;
    }
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new GachaFurnaceEntity(pos, state);
  }

}

