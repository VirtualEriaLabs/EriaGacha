package com.eriagacha.item;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class AgricultureEssenceItem extends Item {
  public AgricultureEssenceItem() {
    super(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64).fireproof()
        .rarity(Rarity.RARE));
  }

  @Override
  public int getMaxUseTime(ItemStack itemstack) {
    return 0;
  }

  @Override
  public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
    return (float) (1F);
  }

  @Environment(EnvType.CLIENT)
  @Override
  public boolean hasGlint(ItemStack stack) {
    return false;
  }

  @Override
  @Environment(EnvType.CLIENT)
  public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                            TooltipContext context) {
    tooltip.add(new LiteralText("text.agriculture_essence_item"));
  }

  @Override
  public int getEnchantability() {
    return 0;
  }
}
