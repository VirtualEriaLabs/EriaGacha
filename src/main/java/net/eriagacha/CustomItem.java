package net.eriagacha;

import lombok.extern.log4j.Log4j2;
import net.eriagacha.network.NetworkClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Log4j2
public class CustomItem extends Item {

  public CustomItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    if (this.isFood()) {
      ItemStack itemStack = user.getStackInHand(hand);
      if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
        user.setCurrentHand(hand);
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
          try {
            if (world.isClient()) {
              NetworkClient.clientToServerGachaRoll(new ItemStack(this));
            }
          } catch (Exception e) {
            log.fatal("Exception CustomItem at use() - Message : {}",
                e.getMessage());
          }
        }
        return TypedActionResult.consume(itemStack);
      } else {
        return TypedActionResult.fail(itemStack);
      }
    } else {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }
  }
}
