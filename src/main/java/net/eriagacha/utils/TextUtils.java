package net.eriagacha.utils;

import net.minecraft.text.TranslatableText;

public class TextUtils {

  public static String translatedTextToString(String keyOfTranslatedText){
    return new TranslatableText(keyOfTranslatedText).getString();
  }
}
