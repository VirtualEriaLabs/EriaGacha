package com.eriagacha.gui;

import com.eriagacha.item.GachaTable.GachaTableEntity;
import com.eriagacha.register.RegisterItems;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class GachaTableGui extends SyncedGuiDescription {

  private static final int INVENTORY_SIZE = 8;

  private final GachaTableEntity gachaTableEntity;


  public GachaTableGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, GachaTableEntity gachaTableEntity) {
    super(RegisterItems.SCREEN_HANDLER_INVENTORY_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));
    this.gachaTableEntity = gachaTableEntity;
    WGridPanel root = new WGridPanel();
    this.setRootPanel(root);
    root.setSize(300, 200);
    root.setInsets(Insets.ROOT_PANEL);

    WItemSlot itemSlot = WItemSlot.of(gachaTableEntity, 0);
    root.add(itemSlot, 4, 1);

    root.add(this.createPlayerInventoryPanel(), 0, 3);

    root.validate(this);
  }

  public GachaTableGui(int syncId, PlayerInventory inv, GachaTableEntity gachaTableEntity) {
    super(RegisterItems.SCREEN_HANDLER_INVENTORY_TYPE, syncId, inv);
    this.gachaTableEntity = gachaTableEntity;

    WGridPanel root = new WGridPanel();
    this.setRootPanel(root);
    root.setSize(300, 200);
    root.setInsets(Insets.ROOT_PANEL);

    WItemSlot itemSlot = WItemSlot.of(gachaTableEntity, 0);
    root.add(itemSlot, 4, 1);

    root.add(this.createPlayerInventoryPanel(), 0, 3);

    root.validate(this);
  }
}
