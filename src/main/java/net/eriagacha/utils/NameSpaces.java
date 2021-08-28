package net.eriagacha.utils;

import net.eriagacha.register.RegisterItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class NameSpaces {

  public static final String PROJECT_NAME = "eriagacha";

  private NameSpaces() {
  }

  public class Network
  {
    public static final Identifier ID_C2S_SEND_GACHA = new Identifier("send:gacha");
    public static final Identifier ID_S2C_RESPONSE_GACHA = new Identifier("response:gacha");

    private Network() {
    }
  }

  public class GachaItems
  {
    public static final ItemStack CHEAP_GACHA_REQUIEREMENT = new ItemStack(RegisterItems.ACQUAINT_FATE);
    public static final ItemStack EXPENSIVE_GACHA_REQUIEREMENT = new ItemStack(RegisterItems.INTERTWINED_FATE);

    private GachaItems() {
    }
  }
}

