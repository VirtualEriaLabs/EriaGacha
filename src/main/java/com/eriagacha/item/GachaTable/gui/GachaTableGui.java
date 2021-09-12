package com.eriagacha.gui;

import com.eriagacha.register.RegisterItems;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class GachaTableGui extends SyncedGuiDescription {

  /*
  public GachaTableGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
    this(
        syncId,
        playerInventory,
        context,
        context.get((world, pos) -> (GachaTableEntity) world.getBlockEntity(pos))
            .orElseThrow(() -> new IllegalArgumentException(
                "FrameGuiDescription can only be used with FrameBlockEntity."))
    );
  }
*/

  public GachaTableGui(int syncId, PlayerInventory inv, ScreenHandlerContext ctx) {
    super(RegisterItems.SCREEN_HANDLER_INVENTORY_TYPE, syncId, inv,
        getBlockInventory(ctx, 9), getBlockPropertyDelegate(ctx));

    WGridPanel root = new WGridPanel();
    this.setRootPanel(root);
    root.setSize(300, 200);
    root.setInsets(Insets.ROOT_PANEL);

    WItemSlot itemSlot = WItemSlot.of(super.blockInventory, 0);
    root.add(itemSlot, 4, 1);

    root.add(this.createPlayerInventoryPanel(), 0, 3);
    root.validate(this);
  }
}
