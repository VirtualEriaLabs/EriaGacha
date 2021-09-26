package com.eriagacha.register;

import static com.eriagacha.register.RegisterBlock.GACHA_BENCH;
import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.AcquaintFate;
import com.eriagacha.item.AdeptusTemptation;
import com.eriagacha.item.EriaLogo;
import com.eriagacha.item.EssenceSwordTool;
import com.eriagacha.item.GachaFurnace.GachaFurnaceItem;
import com.eriagacha.item.GachaTable.GachaTableItem;
import com.eriagacha.item.InterwinedFate;
import com.eriagacha.item.MineralEssenceItem;
import com.eriagacha.item.ScrollItem;
import com.eriagacha.item.gachabench.GachaBenchItem;
import lombok.extern.log4j.Log4j2;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

@Log4j2
public class RegisterItem {
  public static final Item INTERTWINED_FATE_ITEM = new InterwinedFate();
  public static final Item ACQUAINT_FATE_ITEM = new AcquaintFate();
  public static final Item ADEPTUS_TEMPTATION_ITEM = new AdeptusTemptation();
  public static final Item MINERAL_ESSENCE_ITEM = new MineralEssenceItem();
  public static final Item SCROLL_ITEM = new ScrollItem();
  public static final Item ESSENCE_SWORD_ITEM = EssenceSwordTool.INSTANCE;
  public static final Item ERIA_LOGO_ITEM = new EriaLogo();
  public static final Item GACHA_TABLE_ITEM = new GachaTableItem();
  public static final Item GACHA_FURNACE_ITEM = new GachaFurnaceItem();
  public static final Item GACHA_BENCH_ITEM = new GachaBenchItem(GACHA_BENCH, new Item.Settings().group(
      ItemGroup.DECORATIONS));

  private RegisterItem() {}

  public static void init() {
    Registry.register(Registry.ITEM, id("interwined_fate"), INTERTWINED_FATE_ITEM);
    Registry.register(Registry.ITEM, id("acquaint_fate"), ACQUAINT_FATE_ITEM);
    Registry.register(Registry.ITEM, id("adeptus_temptation"), ADEPTUS_TEMPTATION_ITEM);
    Registry.register(Registry.ITEM, id("eria_logo"), ERIA_LOGO_ITEM);
    Registry.register(Registry.ITEM, id("essence_sword"), ESSENCE_SWORD_ITEM);
    Registry.register(Registry.ITEM, id("mineral_essence"), MINERAL_ESSENCE_ITEM);
    Registry.register(Registry.ITEM, id("scroll"), SCROLL_ITEM);
    Registry.register(Registry.ITEM, id("gacha_table"), GACHA_TABLE_ITEM);
    Registry.register(Registry.ITEM, id("gacha_furnace"), GACHA_FURNACE_ITEM);
    Registry.register(Registry.ITEM, id("gacha_bench"), GACHA_BENCH_ITEM);
  }
}
