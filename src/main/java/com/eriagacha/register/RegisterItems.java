package com.eriagacha.register;

import com.eriagacha.howitsdone.block.EssenceOreBlock;
import com.eriagacha.item.AcquaintFate;
import com.eriagacha.item.AdeptusTemptation;
import com.eriagacha.item.EriaLogo;
import com.eriagacha.item.EssenceItem;
import com.eriagacha.item.EssenceOreItem;
import com.eriagacha.item.EssenceSwordTool;
import com.eriagacha.item.InterwinedFate;
import com.eriagacha.item.ScrollItem;
import com.eriagacha.utils.NameSpaces;
import lombok.extern.log4j.Log4j2;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

  private RegisterItems() {
  }

  public static void init() {
    Registry.register(Registry.ITEM, id("interwined_fate"), INTERTWINED_FATE);
    Registry.register(Registry.ITEM, id("acquaint_fate"), ACQUAINT_FATE);
    Registry.register(Registry.ITEM, id("adeptus_temptation"), ADEPTUS_TEMPTATION);
    Registry.register(Registry.ITEM, id("eria_logo"), ERIA_LOGO);
    Registry.register(Registry.ITEM, id("essence_sword"), EssenceSword_ITEM);
    Registry.register(Registry.ITEM, id("essence"), Essence_ITEM);
    Registry.register(Registry.ITEM, id("essence_ore"), EssenceOre_ITEM);
    Registry.register(Registry.BLOCK, id("essence_ore"), EssenceOre_BLOCK);
    Registry.register(Registry.ITEM, id("scroll"), Scroll_ITEM);
  }

  public static final Identifier id(String identifierName) {
    return new Identifier(NameSpaces.PROJECT_NAME, identifierName);
  }

  public static final StatusEffectInstance status(StatusEffect status, int dur, int amp) {
    return new StatusEffectInstance(status, dur, amp);
  }

}
