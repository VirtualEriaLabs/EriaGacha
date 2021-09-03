package net.eriagacha.giveable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import net.eriagacha.api.MinecraftGivable;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

@Builder
@Getter
@EqualsAndHashCode
public class GivableItemStack implements MinecraftGivable {

  @NonNull
  final ItemStack itemStack;

  @Override
  public MinecraftGivable apply(ServerPlayerEntity spe) {
    if (itemStack == null) {
      this.errorFeedback(spe);
      return this;
    }
    spe.getInventory()
        .insertStack(new ItemStack(this.itemStack.getItem(), this.getItemStack().getCount()));
    this.successFeedback(spe, new TranslatableText(""));
    return this;
  }
}
