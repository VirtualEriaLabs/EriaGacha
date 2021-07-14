package net.gacheria;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class GacheriaMod implements ModInitializer {

		private static final Item GACHA_COOKIE = new Item(new Item.Settings().group(ItemGroup.FOOD).maxCount(3));
		@Override
		public void onInitialize() {
			Registry.register(Registry.ITEM, new Identifier("gacheria", "gacha_cookie"), GACHA_COOKIE);

			RegisterMyCommands.init();

	}
}
