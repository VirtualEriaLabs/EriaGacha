package com.eriagacha.item.kartografi;

import static com.eriagacha.register.RegisterBlockEntity.GACHA_BENCH_ENTITY;

import blue.endless.jankson.annotation.Nullable;
import com.eriagacha.item.kartografi.gui.KartografiGui;
import com.eriagacha.register.RegisterItem;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Log4j2
public class KartografiEntity extends LockableContainerBlockEntity implements
    ExtendedScreenHandlerFactory, BlockEntityClientSerializable, PropertyDelegateHolder {

  public final static int INVENTORY_SIZE = 7;
  public final static int BASE = 0;
  public final static int CRAFT_RESULT = 6;
  public final static int[] ESSENCE_INDEX = new int[] {
      1,2,3,4,5
  };
  private final DefaultedList<ItemStack>
      inventory = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
  public static final int DEFAULT_COLOR = 0xA06540;
  public int color = DEFAULT_COLOR;
  private int signTime;
  public int fuel;
  private Item itemSigning;
  protected final PropertyDelegate propertyDelegate;

  public KartografiEntity(BlockPos pos, BlockState state) {
    super(GACHA_BENCH_ENTITY, pos, state);

    this.propertyDelegate = new PropertyDelegate() {
      @Override
      public int get(int index) {
        switch (index) {
          case 0:
            return KartografiEntity.this.signTime;
          case 1:
            return KartografiEntity.this.fuel;
          default:
            return 0;
        }
      }

      @Override
      public void set(int index, int value) {
        switch (index) {
          case 0:
            KartografiEntity.this.signTime = value;
            break;
          case 1:
            KartografiEntity.this.fuel = value;
        }

      }

      @Override
      public int size() {
        return 2;
      }
    };
  }

  @Override
  protected Text getContainerName() {
    return new TranslatableText("text.eriagacha.contract_table");
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
  public boolean isValid(int slot, ItemStack stack) {
    if (slot == 0 && stack.isItemEqual(new ItemStack(RegisterItem.BASE_SCROLL_ITEM))) {
      return true;
    } else if (slot > 0 && slot < CRAFT_RESULT &&
        stack.isItemEqual(new ItemStack(RegisterItem.MINERAL_ESSENCE_ITEM))) {
      return true;
    } else if (slot == CRAFT_RESULT) {
      return false;
    }
    return false;
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    //assert this.world != null;
    if (this.world.getBlockEntity(this.pos) != this) {
      return false;
    }
    return player.squaredDistanceTo(
        (double) this.pos.getX() + 0.5D,
        (double) this.pos.getY() + 0.5D,
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

  @Override
  public PropertyDelegate getPropertyDelegate() {
    return this.propertyDelegate;
  }

  @Nullable
  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return new KartografiGui(syncId, inv, ScreenHandlerContext.create(this.world, this.pos));
  }

  @Override
  protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
    return this.createMenu(syncId, playerInventory, playerInventory.player);
  }

  public static void tick(World world, BlockPos pos, BlockState state,
                          KartografiEntity blockEntity) {

    if (blockEntity.fuel <= 0 && blockEntity.inventory.get(0).isOf(RegisterItem.BASE_SCROLL_ITEM)) {
      blockEntity.fuel = 1;
      blockEntity.inventory.get(0).decrement(1);
      markDirty(world, pos, state);
    }
    boolean canCraft = canCraft(blockEntity.inventory);
    boolean signActive = blockEntity.signTime < 200;

    if (signActive) {
      blockEntity.signTime++;
      boolean signTimeConsumed = blockEntity.signTime == 0;
      if (signTimeConsumed && canCraft) {
        craft(world, pos, blockEntity.inventory);
        markDirty(world, pos, state);
      }
    } else if (canCraft && blockEntity.fuel > 0) {
      --blockEntity.fuel;
      blockEntity.signTime = 0;
      blockEntity.itemSigning = blockEntity.inventory.get(1).getItem();
      if (blockEntity.inventory.get(CRAFT_RESULT).getItem() == RegisterItem.ACQUAINT_FATE_ITEM
          || blockEntity.inventory.get(CRAFT_RESULT).getItem() == Items.AIR) {

        blockEntity.inventory.set(CRAFT_RESULT, new ItemStack(RegisterItem.ACQUAINT_FATE_ITEM,
            1 + blockEntity.inventory.get(CRAFT_RESULT).getCount()));
        LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
        lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
        world.spawnEntity(lightningEntity);
      }
      markDirty(world, pos, state);
    }
  }

  private static void craft(World world, BlockPos pos, DefaultedList<ItemStack> slots) {
    for (int i = 1; i < CRAFT_RESULT; ++i) {
      ItemStack itemStack = slots.get(i);
      itemStack.decrement(1);
      slots.set(i, itemStack);
    }
  }

  private static boolean canCraft(DefaultedList<ItemStack> slots) {
    ItemStack itemStack = (ItemStack) slots.get(3);
    if (itemStack.isEmpty()) {
      return false;
    } else {
      int count = 0;
      for (int i = 1; i < CRAFT_RESULT; ++i) {
        ItemStack itemStack2 = (ItemStack) slots.get(i);
        if (!itemStack2.isEmpty() || itemStack2.getItem() != Items.AIR) {
          count++;
        }
        if (count == ESSENCE_INDEX.length) {
          return true;
        }
      }

      return false;
    }
  }

  @Override
  public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
    buf.writeBlockPos(this.pos);
  }

  @Override
  public void fromClientTag(NbtCompound tag) {
    DefaultedList<ItemStack> items = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
    Inventories.readNbt(tag, items);

    for (int i = 0; i < this.inventory.size(); i++) {
      this.inventory.set(i, items.get(i));
    }
  }

  @Override
  public NbtCompound toClientTag(NbtCompound tag) {
    return this.writeNbt(tag);
  }


  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    DefaultedList<ItemStack> items = DefaultedList.ofSize(this.INVENTORY_SIZE, ItemStack.EMPTY);
    Inventories.readNbt(nbt, this.inventory);
    this.signTime = nbt.getShort("SignTime");
    this.fuel = nbt.getByte("Fuel");
  }

  @Override
  public NbtCompound writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putShort("SignTime", (short) this.signTime);
    Inventories.writeNbt(nbt, this.inventory);
    nbt.putByte("Fuel", (byte) this.fuel);
    return nbt;
  }
}
