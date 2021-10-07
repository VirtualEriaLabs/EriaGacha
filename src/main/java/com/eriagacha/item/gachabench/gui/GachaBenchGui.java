package com.eriagacha.item.gachabench.gui;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;
import static com.eriagacha.utils.RegisterUtils.id;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;

public class GachaBenchGui extends SyncedGuiDescription {

  private static final int PROPERTY_COUNT = 2;
  public static int WINDOW_WIDTH = 164;
  public static int WINDOW_HEIGHT = 200;
  public static int X_PLAYER_INVENTORY = 0;
  public static int Y_PLAYER_INVENTORY = 130;
  private PropertyDelegate blockPropertyDelegate;
  private static final Texture barBg = new
      Texture(id("textures/gui/magic_circle_bg.png"));
  private static final Texture fillBg = new
      Texture(id("textures/gui/magic_circle_fill.png"));

  private static final int[][] SLOTS_POS = new int[][] {
      {72, 1}, {27, 29},
      {118, 29}, {27, 83},
      {118, 83}, {72, 108},
      {72, 56}
  };

  public GachaBenchGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext ctx) {
    super(SCREEN_HANDLER_BENCH_TYPE, syncId, playerInventory,
        getBlockInventory(ctx), getBlockPropertyDelegate(ctx, PROPERTY_COUNT));
    WGridPanel rootGrid = new WGridPanel(1);
    rootGrid.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
    rootGrid.setInsets(Insets.ROOT_PANEL);
    this.setRootPanel(rootGrid);
    WBar newBar = WBar.withConstantMaximum(barBg, fillBg, 0, 200, WBar.Direction.UP);
    newBar.setProperties(this.blockPropertyDelegate);
    rootGrid.add(newBar,25,0,114,128);
    for (var i = 0; i < SLOTS_POS.length; i++) {
      WItemSlot baseItem = WItemSlot.of(super.blockInventory, i);
      rootGrid.add(baseItem, SLOTS_POS[i][0], SLOTS_POS[i][1]);
    }
    rootGrid.add(this.createPlayerInventoryPanel(false), X_PLAYER_INVENTORY, Y_PLAYER_INVENTORY);
    rootGrid.validate(this);

  }
}
