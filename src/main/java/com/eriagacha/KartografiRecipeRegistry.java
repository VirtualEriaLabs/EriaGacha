package com.eriagacha;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;

public class KartografiRecipeRegistry {

  private static final List<KartografiRecipeRegistry.Recipe<Item>> BASE_RECIPES = Lists.newArrayList();
  private static final List<KartografiRecipeRegistry.Recipe<Item>> INGRIDIENTS_RECIPES = Lists.newArrayList();


  static class Recipe<T> {
    final T input;
    final Ingredient ingredient;
    final T output;

    public Recipe(T input, Ingredient ingredient, T output) {
      this.input = input;
      this.ingredient = ingredient;
      this.output = output;
    }
  }
}
