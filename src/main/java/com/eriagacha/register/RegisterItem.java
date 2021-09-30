package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlock.GACHA_BENCH;
import static com.eriagacha.utils.RegisterUtils.id;
import static net.minecraft.util.registry.Registry.ITEM;
import static net.minecraft.util.registry.Registry.register;

import com.eriagacha.item.AcquaintFate;
import com.eriagacha.item.AdeptusTemptation;
import com.eriagacha.item.BaseScrollItem;
import com.eriagacha.item.EriaLogo;
import com.eriagacha.item.EssenceSwordTool;
import com.eriagacha.item.GachaFurnace.GachaFurnaceItem;
import com.eriagacha.item.GachaTable.GachaTableItem;
import com.eriagacha.item.InterwinedFate;
import com.eriagacha.item.MineralEssenceItem;
import com.eriagacha.item.SealedScrollItem;
import com.eriagacha.item.gachabench.GachaBenchItem;
import lombok.extern.log4j.Log4j2;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

@Log4j2
public class RegisterItem {
  public static final Item INTERTWINED_FATE_ITEM = new InterwinedFate();
  public static final Item ACQUAINT_FATE_ITEM = new AcquaintFate();
  public static final Item ADEPTUS_TEMPTATION_ITEM = new AdeptusTemptation();
  public static final Item MINERAL_ESSENCE_ITEM = new MineralEssenceItem();
  public static final Item BASE_SCROLL_ITEM = new BaseScrollItem();
  public static final Item SEALED_SCROLL_ITEM = new SealedScrollItem();
  public static final Item ESSENCE_SWORD_ITEM = EssenceSwordTool.INSTANCE;
  public static final Item ERIA_LOGO_ITEM = new EriaLogo();
  public static final Item GACHA_TABLE_ITEM = new GachaTableItem();
  public static final Item GACHA_FURNACE_ITEM = new GachaFurnaceItem();
  public static final Item GACHA_BENCH_ITEM = new GachaBenchItem(GACHA_BENCH, new Item.Settings().group(
      ItemGroup.DECORATIONS));


  private RegisterItem() {}

  public static void init() {
    register(ITEM, id("interwined_fate"), INTERTWINED_FATE_ITEM);
    register(ITEM, id("acquaint_fate"), ACQUAINT_FATE_ITEM);
    register(ITEM, id("adeptus_temptation"), ADEPTUS_TEMPTATION_ITEM);
    register(ITEM, id("eria_logo"), ERIA_LOGO_ITEM);
    register(ITEM, id("essence_sword"), ESSENCE_SWORD_ITEM);
    register(ITEM, id("mineral_essence"), MINERAL_ESSENCE_ITEM);
    register(ITEM, id("base_scroll"), BASE_SCROLL_ITEM);
    register(ITEM, id("sealed_scroll"), SEALED_SCROLL_ITEM);
    register(ITEM, id("gacha_table"), GACHA_TABLE_ITEM);
    register(ITEM, id("gacha_furnace"), GACHA_FURNACE_ITEM);
    register(ITEM, id("gacha_bench"), GACHA_BENCH_ITEM);
  }
}
