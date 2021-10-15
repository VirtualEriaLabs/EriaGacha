package com.eriagacha.register;

import static com.eriagacha.utils.RegisterUtils.id;

import com.eriagacha.item.kartografi.KartografiBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class RegisterBlock {
  public static final Block GACHA_BENCH = new KartografiBlock(
      AbstractBlock.Settings.of(Material.STONE).strength(1F).sounds(BlockSoundGroup.ANVIL));

  public static void init() {
    Registry.register(Registry.BLOCK, id("gacha_bench"), GACHA_BENCH);
  }

}
