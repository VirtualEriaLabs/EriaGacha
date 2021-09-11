package com.eriagacha.item.GachaTable;

import com.eriagacha.ImplementedInventory;
import com.eriagacha.gui.GachaTableGui;
import com.eriagacha.register.RegisterItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GachaTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory,
    ImplementedInventory {

  private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

  public GachaTableEntity(BlockPos pos, BlockState state) {
    super(RegisterItems.GACHA_TABLE_ENTITY, pos, state);
  }

  @Override
  public DefaultedList<ItemStack> getItems() {
    return this.inventory;
  }

  @Nullable
  @Override
  public  ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return new GachaTableGui(syncId, inv, this);
  }

  @Override
  public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
    packetByteBuf.writeBlockPos(this.pos);
  }

  @Override
  public Text getDisplayName() {
    return new TranslatableText(this.getCachedState().getBlock().getTranslationKey());
  }


}