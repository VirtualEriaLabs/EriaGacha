package net.eriagacha;

import java.util.Random;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class RichSaplingGenerator extends SaplingGenerator {
  private final ConfiguredFeature<TreeFeatureConfig, ?> feature;

  public RichSaplingGenerator(ConfiguredFeature<?, ?> feature) {
    this.feature = (ConfiguredFeature<TreeFeatureConfig, ?>) feature;
  }

  @Override
  protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
    return this.feature;
  }
}

