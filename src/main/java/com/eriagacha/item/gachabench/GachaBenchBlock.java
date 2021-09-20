package com.eriagacha.item.gachabench;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;

import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GachaBenchBlock extends Block implements NamedScreenHandlerFactory {
  public GachaBenchBlock(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                            Hand hand, BlockHitResult hit) {
    if (world.isClient) {
      return ActionResult.SUCCESS;
    }
    Optional.ofNullable(this.createScreenHandlerFactory(state, world, pos))
        .ifPresent(player::openHandledScreen);
    return ActionResult.CONSUME;
  }

  @Nullable
  @Override
  public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world,
                                                              BlockPos pos) {
    return state.getBlock() instanceof NamedScreenHandlerFactory namedScreenHandlerFactory ?
        namedScreenHandlerFactory : null;
  }

  @Override
  public Text getDisplayName() {
    return new LiteralText("GachaBench");
  }

  @Nullable
  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return SCREEN_HANDLER_BENCH_TYPE.create(syncId, inv);
  }
}
