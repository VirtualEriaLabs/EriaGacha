package com.eriagacha;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface ImplementedInventory extends Inventory {

  /**
   * Retrieves the item list of this inventory.
   * Must return the same instance every time it's called.
   */
  DefaultedList<ItemStack> getItems();

  /**
   * Creates an inventory from the item list.
   */
  static ImplementedInventory of(DefaultedList<ItemStack> items) {
    return () -> items;
  }

  /**
   * Creates a new inventory with the specified size.
   */
  static ImplementedInventory ofSize(int size) {
    return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
  }

  /**
   * Returns the inventory size.
   */
  @Override
  default int size() {
    return this.getItems().size();
  }

  /**
   * Checks if the inventory is empty.
   * @return true if this inventory has only empty stacks, false otherwise.
   */
  @Override
  default boolean isEmpty() {
    for (int i = 0; i < this.size(); i++) {
      ItemStack stack = this.getStack(i);
      if (!stack.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Retrieves the item in the slot.
   */
  @Override
  default ItemStack getStack(int slot) {
    return this.getItems().get(slot);
  }

  /**
   * Removes items from an inventory slot.
   * @param slot  The slot to remove from.
   * @param count How many items to remove. If there are less items in the slot than what are requested,
   *              takes all items in that slot.
   */
  @Override
  default ItemStack removeStack(int slot, int count) {
    ItemStack result = Inventories.splitStack(this.getItems(), slot, count);
    if (!result.isEmpty()) {
      this.markDirty();
    }
    return result;
  }

  /**
   * Removes all items from an inventory slot.
   * @param slot The slot to remove from.
   */
  @Override
  default ItemStack removeStack(int slot) {
    return Inventories.removeStack(this.getItems(), slot);
  }

  /**
   * Replaces the current stack in an inventory slot with the provided stack.
   * @param slot  The inventory slot of which to replace the itemstack.
   * @param stack The replacing itemstack. If the stack is too big for
   *              this inventory ({@link Inventory#getMaxCountPerStack()}),
   *              it gets resized to this inventory's maximum amount.
   */
  @Override
  default void setStack(int slot, ItemStack stack) {
    this.getItems().set(slot, stack);
    if (stack.getCount() > this.getMaxCountPerStack()) {
      stack.setCount(this.getMaxCountPerStack());
    }
  }

  /**
   * Clears the inventory.
   */
  @Override
  default void clear() {
    this.getItems().clear();
  }

  /**
   * Marks the state as dirty.
   * Must be called after changes in the inventory, so that the game can properly save
   * the inventory contents and notify neighboring blocks of inventory changes.
   */
  @Override
  default void markDirty() {
    // Override if you want behavior.
  }

  /**
   * @return true if the player can use the inventory, false otherwise.
   */
  @Override
  default boolean canPlayerUse(PlayerEntity player) {
    return true;
  }
}
