package com.eriagacha.item.gachabench.gui;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class GachaBenchGui extends SyncedGuiDescription {

  /*
  public GachaBenchGui(int syncId,PlayerInventory playerInventory) {
    this(RegisterScreen.SCREEN_HANDLER_BENCH_TYPE, syncId, playerInventory);
  }
  */
  public GachaBenchGui(int syncId,
                       PlayerInventory playerInventory, ScreenHandlerContext ctx) {
    super(SCREEN_HANDLER_BENCH_TYPE, syncId, playerInventory, getBlockInventory(ctx), getBlockPropertyDelegate(ctx));
    WGridPanel root = new WGridPanel(1);
    this.setRootPanel(root);
    root.setSize(164, 200);
    root.setInsets(Insets.ROOT_PANEL);


    WItemSlot baseItem = WItemSlot.of(super.blockInventory, 0);
    root.add(baseItem, 73, 8);

    WItemSlot row1Item1 = WItemSlot.of(super.blockInventory, 1);
    root.add(row1Item1, 42, 32);
    WItemSlot row1Item2 = WItemSlot.of(super.blockInventory, 2);
    root.add(row1Item2, 104, 32);

    WItemSlot row2Item1 = WItemSlot.of(super.blockInventory, 3);
    root.add(row2Item1, 16, 63);
    WItemSlot row2Item2 = WItemSlot.of(super.blockInventory, 4);
    root.add(row2Item2, 132, 63);

    WItemSlot row3Item1 = WItemSlot.of(super.blockInventory, 5);
    root.add(row3Item1, 42, 95);
    WItemSlot row3Item2 = WItemSlot.of(super.blockInventory, 6);
    root.add(row3Item2, 104, 95);

    WItemSlot result = WItemSlot.of(super.blockInventory, 7);
    root.add(result, 74, 63);

    int YPlayerInventory = 130, XPlayerInventory = 0;
    root.add(this.createPlayerInventoryPanel(false), XPlayerInventory, YPlayerInventory);
    root.validate(this);
  }

  public GachaBenchGui(ScreenHandlerType<?> type, int syncId,
                       PlayerInventory playerInventory) {
    super(type, syncId, playerInventory);
    WGridPanel root = new WGridPanel();
    this.setRootPanel(root);
    root.setSize(150, 150);
    root.setInsets(Insets.ROOT_PANEL);
    root.validate(this);
  }

  public GachaBenchGui(ScreenHandlerType<?> type, int syncId,
                       PlayerInventory playerInventory,
                       @Nullable Inventory blockInventory,
                       @Nullable PropertyDelegate propertyDelegate) {
    super(type, syncId, playerInventory, blockInventory, propertyDelegate);
  }
}
