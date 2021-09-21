package com.eriagacha.item.gachabench.gui;

import com.eriagacha.register.RegisterScreen;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class GachaBenchGui extends SyncedGuiDescription {

  public GachaBenchGui(int syncId,PlayerInventory playerInventory) {
    this(RegisterScreen.SCREEN_HANDLER_BENCH_TYPE, syncId, playerInventory);
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
