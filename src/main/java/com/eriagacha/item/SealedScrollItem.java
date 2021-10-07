package com.eriagacha.item;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class SealedScrollItem extends Item {

  public SealedScrollItem() {
    super(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64).rarity(Rarity.RARE));
  }

  @Override
  @Environment(EnvType.CLIENT)
  public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                            TooltipContext context) {
    tooltip.add(new TranslatableText("text.eriagacha.sealed_scroll_description_0"));
    tooltip.add(new TranslatableText("text.eriagacha.sealed_scroll_description_1"));
  }

}
