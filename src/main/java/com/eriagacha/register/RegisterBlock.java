package com.eriagacha.register;

import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.GachaFurnace.GachaFurnaceBlock;
import com.eriagacha.item.GachaTable.GachaTableBlock;
import com.eriagacha.item.gachabench.GachaBenchBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class RegisterBlock {
  public static final Block GACHA_FURNACE = new GachaFurnaceBlock();
  public static final Block GACHA_TABLE = new GachaTableBlock();
  public static final Block GACHA_BENCH = new GachaBenchBlock(
      AbstractBlock.Settings.of(Material.STONE).strength(1F).sounds(BlockSoundGroup.ANVIL));

  public static void init() {
    Registry.register(Registry.BLOCK, id("gacha_table"), GACHA_TABLE);
    Registry.register(Registry.BLOCK, id("gacha_furnace"), GACHA_FURNACE);
    Registry.register(Registry.BLOCK, id("gacha_bench"), GACHA_BENCH);
  }

}
