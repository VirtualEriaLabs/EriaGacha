package com.eriagacha.item.GachaFurnace.gui;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_INVENTORY_TYPE;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class GachaFurnaceGui extends SyncedGuiDescription {

  public GachaFurnaceGui(int syncId, PlayerInventory inv, ScreenHandlerContext ctx) {
    super(SCREEN_HANDLER_INVENTORY_TYPE, syncId, inv,
        getBlockInventory(ctx), getBlockPropertyDelegate(ctx));
    if(super.canUse(inv.player))
    {

      WGridPanel root = new WGridPanel();
      this.setRootPanel(root);
      root.setSize(150, 150);
      root.setInsets(Insets.ROOT_PANEL);
      int YPlayerInventory = 3, XPlayerInventory = 0;
      int mult = 0, XPointOfStart = -1, YPointOfStart = 1, cont = 0;
      for (int i = 0; i < this.blockInventory.size(); i++) {
        if (cont == 10) {
          YPlayerInventory++;
          YPointOfStart++;
          mult = 0;
          cont = 0;
        }
        mult++;
        cont++;
        WItemSlot itemSlot = WItemSlot.of(super.blockInventory, i);
        root.add(itemSlot, XPointOfStart + mult, YPointOfStart);
      }
      root.add(this.createPlayerInventoryPanel(), XPlayerInventory, YPlayerInventory);
      root.validate(this);
    }else{
      super.close(inv.player);
    }
  }
}
