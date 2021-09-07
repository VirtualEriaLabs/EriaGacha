package net.eriagacha.howitsdone.block;

import net.eriagacha.register.RegisterItems;
import net.eriagacha.utils.NameSpaces;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class essenceOreGeneration {

  private static final ConfiguredFeature<?, ?> ORE_ESSENCE_OVERWORLD = Feature.ORE
      .configure(new OreFeatureConfig(
          OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, // Base block is end stone in The End biomes
          RegisterItems.EssenceOre_BLOCK.getDefaultState(),
          9))
      .range(new RangeDecoratorConfig(
          UniformHeightProvider.create(YOffset.fixed(0), YOffset.fixed(64))))
      .spreadHorizontally()
      .repeat(20);


  public void init() {

    RegistryKey<ConfiguredFeature<?, ?>> essenceOreOverWorld =
        RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(NameSpaces.PROJECT_NAME, "essence_ore"));

    BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
        GenerationStep.Feature.UNDERGROUND_ORES, essenceOreOverWorld);

    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, essenceOreOverWorld.getValue(),
        ORE_ESSENCE_OVERWORLD);
  }
}
