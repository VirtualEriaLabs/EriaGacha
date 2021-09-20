package com.eriagacha.item.gachabench.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class GachaBenchGui extends SyncedGuiDescription {

  public GachaBenchGui(ScreenHandlerType<?> type, int syncId,
                       PlayerInventory playerInventory) {
    super(type, syncId, playerInventory);
  }

  public GachaBenchGui(ScreenHandlerType<?> type, int syncId,
                       PlayerInventory playerInventory,
                       @Nullable Inventory blockInventory,
                       @Nullable PropertyDelegate propertyDelegate) {
    super(type, syncId, playerInventory, blockInventory, propertyDelegate);
  }
}
