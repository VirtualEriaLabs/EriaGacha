package net.eriagacha.howitsdone.customtree;

import net.eriagacha.utils.NameSpaces;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RichSaplingBlock extends SaplingBlock {
  public RichSaplingBlock(SaplingGenerator generator, Settings settings) {
    super(generator, settings);
  }

  public static final RichSaplingBlock RICH_SAPLING =
      new RichSaplingBlock(new RichSaplingGenerator(TreeRich.TREE_RICH),
          FabricBlockSettings.copyOf(
              Blocks.OAK_SAPLING));

  public static void register() {
    Registry.register(Registry.BLOCK, new Identifier(NameSpaces.PROJECT_NAME, "rich_sapling"),
        RICH_SAPLING);
    Registry.register(Registry.ITEM, new Identifier(NameSpaces.PROJECT_NAME, "rich_sapling"),
        new BlockItem(RICH_SAPLING, new Item.Settings().group(ItemGroup.MISC)));
  }

}
