package com.eriagacha.item.gachabench.gui;

import static com.eriagacha.register.RegisterScreen.SCREEN_HANDLER_BENCH_TYPE;

import com.eriagacha.utils.NameSpaces;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class GachaBenchGui extends SyncedGuiDescription {

  private static final int PROPERTY_COUNT = 2; // This should match PropertyDelegate.size().
  public static int WINDOW_WIDTH = 164;
  public static int WINDOW_HEIGHT = 200;
  public static int X_PLAYER_INVENTORY = 0;
  public static int Y_PLAYER_INVENTORY = 130;
  private PropertyDelegate blockPropertyDelegate;
  private static final Texture
      barBg =
      new Texture(new Identifier(NameSpaces.PROJECT_NAME, "textures/gui/magic_circle_bg.png"));
  private static final Texture
      fillBg =
      new Texture(new Identifier(NameSpaces.PROJECT_NAME, "textures/gui/magic_circle_fill.png"));

  private static final int[][] SLOTS_POS = new int[][] {
      {73, 8}, {42, 32},
      {104, 32}, {16, 63},
      {132, 63}, {42, 95},
      {104, 95}, {}
  };

  public GachaBenchGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext ctx) {
    super(SCREEN_HANDLER_BENCH_TYPE, syncId, playerInventory,
        getBlockInventory(ctx), getBlockPropertyDelegate(ctx, PROPERTY_COUNT));

    WGridPanel rootGrid = new WGridPanel(1);
    rootGrid.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
    rootGrid.setInsets(Insets.ROOT_PANEL);
    this.setRootPanel(rootGrid);
    WBar newBar = new WBar(barBg, fillBg, 0, 200);

    newBar.setProperties(this.blockPropertyDelegate);
    rootGrid.add(newBar,0,65,64,64);
    for (var i = 0; i <= 6; i++) {
      WItemSlot baseItem = WItemSlot.of(super.blockInventory, i);
      rootGrid.add(baseItem, SLOTS_POS[i][0], SLOTS_POS[i][1]);
    }
    rootGrid.add(this.createPlayerInventoryPanel(false), X_PLAYER_INVENTORY, Y_PLAYER_INVENTORY);
    rootGrid.validate(this);

  }
}
