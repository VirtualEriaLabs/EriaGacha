package com.eriagacha.howitsdone.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class EssenceOreBlock extends Block {

  public EssenceOreBlock() {
    super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.GRAVEL).strength(1f, 10f)
        .luminance(0)
        .breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
  }
}
