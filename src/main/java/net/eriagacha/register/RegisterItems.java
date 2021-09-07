package net.eriagacha.register;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.howitsdone.block.EssenceOreBlock;
import net.eriagacha.item.CustomItem;
import net.eriagacha.item.EssenceItem;
import net.eriagacha.item.EssenceSwordTool;
import net.eriagacha.item.ScrollItem;
import net.eriagacha.utils.NameSpaces;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

@Log4j2
public class RegisterItems {


  //TODO : Investigar PlayerItemConsumeEvent para que se ejecute el comando al comer
  //TODO : Investigate Mixing StatusEffects to dont make 7 calls to statusEffects
  public static final Item INTERTWINED_FATE = new CustomItem(new Item.Settings()
      .group(ItemGroup.MISC)
      .food(new FoodComponent.Builder().hunger(1).saturationModifier(1f).alwaysEdible().build()));

  public static final Item ACQUAINT_FATE = new CustomItem(new Item.Settings()
      .group(ItemGroup.MISC)
      .food(new FoodComponent.Builder().hunger(1).saturationModifier(1f).alwaysEdible().build()));

  public static final Item ADEPTUS_TEMPTATION = new Item(new Item.Settings()
      .group(ItemGroup.MISC)
      .food(new FoodComponent.Builder().hunger(20).saturationModifier(40f).snack().alwaysEdible()
          .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20000, 4), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20000, 8), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20000, 0), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20000, 0), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20000, 2), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 20000, 1), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20000, 1), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20000, 3), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20000, 2), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20000, 0), 2)
          .statusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 20000, 0), 2)
          .build()));
  public static final Item PRIMOGEM =
      new Item(new Item.Settings().group(ItemGroup.MISC).food(
          new FoodComponent.Builder().hunger(1).saturationModifier(1f).snack().alwaysEdible()
              .build()));
  public static final Item ERIA_LOGO =
      new Item(new Item.Settings().group(ItemGroup.DECORATIONS).fireproof().maxCount(1));

  public static final Identifier crimsonambient_ID = id("crimsonambient");
  public static final SoundEvent crimsonambientEvent = new SoundEvent(crimsonambient_ID);
  public static final Identifier crimsonmusic_ID = id("crimsonmusic");
  public static final Identifier bossmusic_ID = id("bossmusic");
  public static final Item Essence_ITEM =
      Registry.register(Registry.ITEM, id("essence"), new EssenceItem());
  public static final Item Scroll_ITEM =
      Registry.register(Registry.ITEM, id("scroll"), new ScrollItem());
  public static final Block EssenceOre_BLOCK =
      Registry.register(Registry.BLOCK, id("essence_ore"), new EssenceOreBlock());
  public static final BlockItem EssenceOre_ITEM =
      Registry.register(Registry.ITEM, id("essence_ore"),
          new BlockItem(EssenceOre_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
  public static final Item EssenceSword_ITEM =
      Registry.register(Registry.ITEM, id("essence_sword"), EssenceSwordTool.INSTANCE);
  public static final RegistryKey<Biome> Crimson_KEY =
      RegistryKey.of(Registry.BIOME_KEY, id("crimson"));


  private RegisterItems() {
  }

  public static final Identifier id(String identifierName) {
    return new Identifier(NameSpaces.PROJECT_NAME, identifierName);
  }


  public static void init() {
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "interwined_fate"),
        INTERTWINED_FATE);
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "acquaint_fate"),
        ACQUAINT_FATE);
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "adeptus_temptation"),
        ADEPTUS_TEMPTATION);
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "primogem"),
        PRIMOGEM);
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "eria_logo"),
        ERIA_LOGO);

  }

}
