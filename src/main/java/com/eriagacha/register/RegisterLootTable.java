package com.eriagacha.register;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class RegisterLootTable {
  private static final Identifier COAL_ORE_LOOT_TABLE_ID = Blocks.COAL_ORE.getLootTableId();
  private static final Identifier DIAMOND_ORE_LOOT_TABLE_ID = Blocks.DIAMOND_ORE.getLootTableId();
  private static final Identifier IRON_ORE_LOOT_TABLE_ID = Blocks.IRON_ORE.getLootTableId();
  private static final Identifier COPPER_ORE_LOOT_TABLE_ID = Blocks.COPPER_ORE.getLootTableId();
  private static final Identifier EMERALD_ORE_LOOT_TABLE_ID = Blocks.EMERALD_ORE.getLootTableId();

  protected static final float[] COAL_ORE_CHANCES = new float[] {0.1f, 0.14285715f, 0.25f, 0.32f};
  protected static final float[] IRON_ORE_CHANCES = new float[] {0.1f, 0.14285715f, 0.25f, 0.37f};
  protected static final float[] DIAMOND_ORE_CHANCES =
      new float[] {0.1f, 0.14285715f, 0.25f, 0.46f};
  protected static final float[] COPPER_ORE_CHANCES = new float[] {0.1f, 0.14285715f, 0.25f, 0.46f};
  protected static final float[] EMERALD_ORE_CHANCES =
      new float[] {0.1f, 0.14285715f, 0.25f, 0.46f};

  // Actual code
  public static void init() {

    LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) ->
    {
      if (COAL_ORE_LOOT_TABLE_ID.equals(id)) {
        table.pool(lootTableHelper(COAL_ORE_CHANCES, RegisterItem.MINERAL_ESSENCE_ITEM));
      } else if (DIAMOND_ORE_LOOT_TABLE_ID.equals(id)) {
        table.pool(lootTableHelper(DIAMOND_ORE_CHANCES, RegisterItem.MINERAL_ESSENCE_ITEM));
      } else if (IRON_ORE_LOOT_TABLE_ID.equals(id)) {
        table.pool(lootTableHelper(IRON_ORE_CHANCES, RegisterItem.MINERAL_ESSENCE_ITEM));
      } else if (COPPER_ORE_LOOT_TABLE_ID.equals(id)) {
        table.pool(lootTableHelper(COPPER_ORE_CHANCES, RegisterItem.MINERAL_ESSENCE_ITEM));
      } else if (EMERALD_ORE_LOOT_TABLE_ID.equals(id)) {
        table.pool(lootTableHelper(EMERALD_ORE_CHANCES, RegisterItem.MINERAL_ESSENCE_ITEM));
      }
    });
  }

  public static FabricLootPoolBuilder lootTableHelper(float[] chances, Item reward) {
    return FabricLootPoolBuilder.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .with(ItemEntry.builder(reward)).conditionally(
            TableBonusLootCondition.builder(Enchantments.FORTUNE, chances));
  }

}
