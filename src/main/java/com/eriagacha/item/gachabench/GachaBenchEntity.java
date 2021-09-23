package com.eriagacha.item.gachabench;

import static com.eriagacha.register.RegisterBlockEntity.GACHA_BENCH_ENTITY;

import blue.endless.jankson.annotation.Nullable;
import com.eriagacha.item.gachabench.gui.GachaBenchGui;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GachaBenchEntity extends LockableContainerBlockEntity implements
    ExtendedScreenHandlerFactory {

  public final int INVENTORY_SIZE = 9;
  private final DefaultedList<ItemStack>
      inventory = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
  public static final int DEFAULT_COLOR = 0xA06540;
  public int color = DEFAULT_COLOR;

  public GachaBenchEntity(BlockPos pos, BlockState state) {
    super(GACHA_BENCH_ENTITY, pos, state);
  }

  @Override
  protected Text getContainerName() {
    return new LiteralText("GachaBench");
  }

  @Override
  public int size() {
    return this.inventory.size();
  }

  @Override
  public boolean isEmpty() {
    for (int i = 0; i < this.size(); i++) {
      ItemStack stack = this.getStack(i);
      if (!stack.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public ItemStack getStack(int slot) {
    this.markDirty();
    return this.inventory.get(slot);
  }

  @Override
  public ItemStack removeStack(int slot, int amount) {
    this.markDirty();
    ItemStack result = Inventories.splitStack(this.inventory, slot, amount);
    if (!result.isEmpty()) {
      this.markDirty();
    }
    return result;
  }

  @Override
  public ItemStack removeStack(int slot) {
    this.markDirty();
    return Inventories.removeStack(this.inventory, slot);
  }

  @Override
  public void setStack(int slot, ItemStack stack) {
    this.markDirty();
    this.inventory.set(slot, stack);
    if (stack.getCount() > this.getMaxCountPerStack()) {
      stack.setCount(this.getMaxCountPerStack());
    }
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    //assert this.world != null;
    if (this.world.getBlockEntity(this.pos) != this) {
      return false;
    }
    return player
        .squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
            (double) this.pos.getZ() + 0.5D) <= 64.0D;
  }

  @Override
  public void clear() {
    this.inventory.clear();
  }

  @Override
  public Text getDisplayName() {
    return new LiteralText("GachaBench");
  }

  @Nullable
  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return  new GachaBenchGui(syncId, inv, ScreenHandlerContext.create(this.world,this.pos));
  }

  @Override
  protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
    return this.createMenu(syncId, playerInventory, playerInventory.player);
  }

  public static void tick(World world, BlockPos pos, BlockState state,
                          GachaBenchEntity blockEntity) {
    //System.out.println("xd");
  }

  @Override
  public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
    buf.writeBlockPos(this.pos);
  }
}
