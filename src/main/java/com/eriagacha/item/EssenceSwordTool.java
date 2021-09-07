package com.eriagacha.item;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class EssenceSwordTool {
  public static final ToolMaterial ESSENCESWORD_TOOL_MATERIAL = new ToolMaterial() {
    @Override
    public int getDurability() {
      return 2400;
    }

    @Override
    public float getMiningSpeedMultiplier() {
      return 4F;
    }

    @Override
    public float getAttackDamage() {
      return 14F;
    }

    @Override
    public int getMiningLevel() {
      return 1;
    }

    @Override
    public int getEnchantability() {
      return 2;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return Ingredient.EMPTY;
    }
  };
  public static final Item INSTANCE = new SwordItem(ESSENCESWORD_TOOL_MATERIAL, 0, (float) -3.8,
      (new FabricItemSettings().group(ItemGroup.COMBAT))) {
    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                              TooltipContext context) {
      tooltip.add(new LiteralText("A sword from some fallen god"));
      tooltip.add(new LiteralText("use at your own risk"));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return true;
    }
  };
}
