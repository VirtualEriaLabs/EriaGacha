package com.eriagacha.register;

import com.eriagacha.howitsdone.block.EssenceOreBlock;
import com.eriagacha.item.AcquaintFate;
import com.eriagacha.item.AdeptusTemptation;
import com.eriagacha.item.EriaLogo;
import com.eriagacha.item.EssenceItem;
import com.eriagacha.item.EssenceOreItem;
import com.eriagacha.item.EssenceSwordTool;
import com.eriagacha.item.GachaFurnace.GachaFurnaceBlock;
import com.eriagacha.item.GachaFurnace.GachaFurnaceEntity;
import com.eriagacha.item.GachaFurnace.GachaFurnaceItem;
import com.eriagacha.item.GachaTable.GachaTableBlock;
import com.eriagacha.item.GachaTable.GachaTableEntity;
import com.eriagacha.item.GachaTable.GachaTableItem;
import com.eriagacha.item.GachaTable.gui.GachaTableGui;
import com.eriagacha.item.InterwinedFate;
import com.eriagacha.item.ScrollItem;
import com.eriagacha.utils.NameSpaces;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Log4j2
public class RegisterItems {

  public static final Item INTERTWINED_FATE = new InterwinedFate();
  public static final Item ACQUAINT_FATE = new AcquaintFate();
  public static final Item ADEPTUS_TEMPTATION = new AdeptusTemptation();
  public static final Item Essence_ITEM = new EssenceItem();
  public static final Item Scroll_ITEM = new ScrollItem();
  public static final Block EssenceOre_BLOCK = new EssenceOreBlock();
  public static final BlockItem EssenceOre_ITEM = new EssenceOreItem();
  public static final Item EssenceSword_ITEM = EssenceSwordTool.INSTANCE;
  public static final Item ERIA_LOGO = new EriaLogo();
  public static final Block GACHA_TABLE = new GachaTableBlock();
  public static final Item GACHA_TABLE_ITEM = new GachaTableItem();


  public static final Block GACHA_FURNACE = new GachaFurnaceBlock();
  public static final Item GACHA_FURNACE_ITEM = new GachaFurnaceItem();
  public static BlockEntityType<GachaTableEntity> GACHA_TABLE_ENTITY;
  public static ScreenHandlerType<GachaTableGui> SCREEN_HANDLER_INVENTORY_TYPE;
  public static BlockEntityType<GachaFurnaceEntity> GACHA_FURNACE_ENTITY;


  static {
    /*
    SCREEN_HANDLER_INVENTORY_TYPE = ScreenHandlerRegistry.registerExtended(id("gacha_table_gui"),
            (syncId, inventory, buf) -> {
              BlockPos pos = buf.readBlockPos();
              return new GachaTableGui(syncId, inventory,
                  ScreenHandlerContext.create(inventory.player.world, pos),
                  (GachaTableEntity) Optional.ofNullable(inventory.player.world.getBlockEntity(pos))
                      .orElseThrow(AssertionError::new));
            });
     */


  }

  private RegisterItems() {}

  public static void init() {
    GACHA_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, id("gacha_table_entity"),
        FabricBlockEntityTypeBuilder.create(GachaTableEntity::new, GACHA_TABLE).build(null));

    GACHA_FURNACE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, id("gacha_furnace_entity"),
        FabricBlockEntityTypeBuilder.create(GachaFurnaceEntity::new, GACHA_FURNACE).build(null));

    SCREEN_HANDLER_INVENTORY_TYPE = ScreenHandlerRegistry.registerExtended(
        id("gacha_table_gui"),
        (syncId, inventory, buf) -> new GachaTableGui(syncId, inventory, ScreenHandlerContext
            .create(inventory.player.world, buf.readBlockPos())));


    Registry.register(Registry.ITEM, id("interwined_fate"), INTERTWINED_FATE);
    Registry.register(Registry.ITEM, id("acquaint_fate"), ACQUAINT_FATE);
    Registry.register(Registry.ITEM, id("adeptus_temptation"), ADEPTUS_TEMPTATION);
    Registry.register(Registry.ITEM, id("eria_logo"), ERIA_LOGO);
    Registry.register(Registry.ITEM, id("essence_sword"), EssenceSword_ITEM);
    Registry.register(Registry.ITEM, id("essence"), Essence_ITEM);
    Registry.register(Registry.ITEM, id("essence_ore"), EssenceOre_ITEM);
    Registry.register(Registry.BLOCK, id("essence_ore"), EssenceOre_BLOCK);
    Registry.register(Registry.ITEM, id("scroll"), Scroll_ITEM);
    Registry.register(Registry.BLOCK, id("gacha_table"), GACHA_TABLE);
    Registry.register(Registry.ITEM, id("gacha_table"), GACHA_TABLE_ITEM);

    Registry.register(Registry.BLOCK, id("gacha_furnace"), GACHA_FURNACE);
    Registry.register(Registry.ITEM, id("gacha_furnace"), GACHA_FURNACE_ITEM);
  }

  public static Identifier id(String identifierName) {
    return new Identifier(NameSpaces.PROJECT_NAME, identifierName);
  }

  public static StatusEffectInstance status(StatusEffect status, int dur, int amp) {
    return new StatusEffectInstance(status, dur, amp);
  }


}
