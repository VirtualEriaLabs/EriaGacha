package com.eriagacha.item;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
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
    tooltip.add(new LiteralText("An unknown scroll"));
    tooltip.add(new LiteralText("Sometimes you can hear sounds coming out of it"));
  }

}
