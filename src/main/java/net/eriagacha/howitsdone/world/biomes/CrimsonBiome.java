package net.eriagacha.howitsdone.world.biomes;


import net.eriagacha.howitsdone.customtree.RichSaplingBlock;
import net.eriagacha.register.RegisterItems;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class CrimsonBiome {
  private static Biome theBiome;
  private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SURFACE_BUILDER =
      SurfaceBuilder.DEFAULT
          .withConfig(new TernarySurfaceConfig(Blocks.GRASS_BLOCK.getDefaultState(),
              Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState(),
              Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()));


  public static void init() {
    Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
        RegisterItems.Crimson_KEY.getValue(), SURFACE_BUILDER);
    BiomeEffects effects =
        new BiomeEffects.Builder().fogColor(-44732).waterColor(-13434829).waterFogColor(-13434829)
            .skyColor(-44732)
            .grassColor(-1295814).foliageColor(-12768475)
            .loopSound((SoundEvent) RegisterItems.crimsonambientEvent)
            .particleConfig(new BiomeParticleConfig(ParticleTypes.SMOKE, 0.002f))
            .particleConfig(new BiomeParticleConfig(ParticleTypes.EXPLOSION, 0.50f))
            .build();
    GenerationSettings.Builder genSettingsBuilder = new GenerationSettings.Builder();
    genSettingsBuilder.feature(
        GenerationStep.Feature.VEGETAL_DECORATION, Feature.TREE
            .configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.NETHERITE_BLOCK.getDefaultState()),
                new StraightTrunkPlacer(8, 3, 0),
                new SimpleBlockStateProvider(Blocks.DIAMOND_BLOCK.getDefaultState()),
                new SimpleBlockStateProvider(RichSaplingBlock.RICH_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(5), ConstantIntProvider.create(0),
                    3),
                new TwoLayersFeatureSize(1, 0,
                    1))
                .build()))
            .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(2, 0.1f, 1))));
    DefaultBiomeFeatures.addLandCarvers(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultOres(genSettingsBuilder);
    DefaultBiomeFeatures.addPlainsTallGrass(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultVegetation(genSettingsBuilder);
    DefaultBiomeFeatures.addDesertDeadBushes(genSettingsBuilder);
    DefaultBiomeFeatures.addJungleGrass(genSettingsBuilder);
    DefaultBiomeFeatures.addSavannaTrees(genSettingsBuilder);
    DefaultBiomeFeatures.addTaigaGrass(genSettingsBuilder);
    DefaultBiomeFeatures.addGiantTaigaGrass(genSettingsBuilder);
    DefaultBiomeFeatures.addMossyRocks(genSettingsBuilder);
    DefaultBiomeFeatures.addInfestedStone(genSettingsBuilder);
    DefaultBiomeFeatures.addDungeons(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultOres(genSettingsBuilder);
    DefaultBiomeFeatures.addSwampVegetation(genSettingsBuilder);
    genSettingsBuilder
        .feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SEAGRASS_NORMAL);
    DefaultBiomeFeatures.addDefaultFlowers(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultGrass(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultMushrooms(genSettingsBuilder);
    DefaultBiomeFeatures.addDefaultVegetation(genSettingsBuilder);
    genSettingsBuilder
        .feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.DISK_GRAVEL);
    genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
    genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
    genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);
    genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.VILLAGE_SAVANNA);
    genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.MANSION);
    genSettingsBuilder.surfaceBuilder(SURFACE_BUILDER);
    SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
    spawnBuilder
        .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 20, 4, 4));
    spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BAT, 20, 4, 4));
    spawnBuilder
        .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 20, 4, 4));
    Biome.Builder biomeBuilder = new Biome.Builder();
    biomeBuilder.effects(effects);
    biomeBuilder.generationSettings(genSettingsBuilder.build());
    biomeBuilder.spawnSettings(spawnBuilder.build());
    biomeBuilder.temperatureModifier(Biome.TemperatureModifier.NONE);
    biomeBuilder.temperature(0.5F);
    biomeBuilder.downfall(0.5F);
    biomeBuilder.depth(0.1F);
    biomeBuilder.scale(0.2F);
    biomeBuilder.category(Biome.Category.NONE);
    biomeBuilder.precipitation(Biome.Precipitation.RAIN);
    theBiome = biomeBuilder.build();
    Registry.register(BuiltinRegistries.BIOME, RegisterItems.Crimson_KEY.getValue(), theBiome);
    OverworldBiomes.addContinentalBiome(RegisterItems.Crimson_KEY,
        OverworldClimate.TEMPERATE, 100d);
  }
}
