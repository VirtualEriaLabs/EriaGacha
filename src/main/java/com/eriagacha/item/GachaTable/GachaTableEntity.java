package com.eriagacha.item.GachaTable;

import com.eriagacha.ImplementedInventory;
import com.eriagacha.item.GachaTable.gui.GachaTableGui;
import com.eriagacha.register.RegisterItems;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class GachaTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory,
    RenderAttachmentBlockEntity, BlockEntityClientSerializable, ImplementedInventory {

  public final int INVENTORY_SIZE = 35;
  private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
  public static final int DEFAULT_COLOR = 0xA06540;
  public int color = DEFAULT_COLOR;

  public GachaTableEntity(BlockPos pos, BlockState state) {
    super(RegisterItems.GACHA_TABLE_ENTITY, pos, state);


  }

  @Override
  public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
    packetByteBuf.writeBlockPos(this.pos);
  }

  @Override
  public NbtCompound writeNbt(NbtCompound nbt) {
    nbt = super.writeNbt(nbt);
    nbt = Inventories.writeNbt(nbt, this.inventory);
    return nbt;
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    Inventories.readNbt(nbt, this.inventory);
    if(nbt.contains("Diamond", 8)) {
      log.info("I'm kinda a diamond");
    }
  }

  @Override
  public void fromClientTag(NbtCompound nbt) {
    DefaultedList<ItemStack> items = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
    Inventories.readNbt(nbt, items);

    for(int i = 0; i < this.inventory.size(); i++) {
      this.inventory.set(i, items.get(i));
    }
  }

  @Override
  public NbtCompound toClientTag(NbtCompound nbt) {
    return this.writeNbt(nbt);

  }

  @Override
  public @Nullable Object getRenderAttachmentData() {
    return this.color;
  }

  @Override
  public DefaultedList<ItemStack> getItems() {
    return this.inventory;
  }

  @Override
  public ItemStack getStack(int slot) {
    return ImplementedInventory.super.getStack(slot);

  }

  @Override
  public ItemStack removeStack(int slot, int count) {
    return ImplementedInventory.super.removeStack(slot, count);
  }

  @Override
  public ItemStack removeStack(int slot) {
    return ImplementedInventory.super.removeStack(slot);
  }

  @Override
  public void setStack(int slot, ItemStack stack) {
    ImplementedInventory.super.setStack(slot, stack);
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    if (this.world.getBlockEntity(this.pos) != this) {
      return false;
    } else {
      return player.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
  }

  @Override
  public void onClose(PlayerEntity player) {

    ImplementedInventory.super.onClose(player);

  }

  @Override
  public Text getDisplayName() {
    return new LiteralText("Mi mesita");
  }

  @Nullable
  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return new GachaTableGui(syncId, inv, ScreenHandlerContext.create(this.world,this.pos));
  }
}