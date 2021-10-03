package com.eriagacha.item;

import static com.eriagacha.network.NetworkServer.serverToClientDrawParticule;
import static com.eriagacha.utils.RegisterUtils.id;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EssenceSwordTool {

  public static final ToolMaterial ESSENCESWORD_TOOL_MATERIAL = new ToolMaterial() {

    @Override
    public int getDurability() {
      return 2400;
    }

    @Override
    public float getMiningSpeedMultiplier() {
      return 4F;
    }

    @Override
    public float getAttackDamage() {
      return 25F;
    }

    @Override
    public int getMiningLevel() {
      return 1;
    }

    @Override
    public int getEnchantability() {
      return 2;
    }

    @Override
    public Ingredient getRepairIngredient() {
      return Ingredient.EMPTY;
    }
  };

  public static final Item INSTANCE = new SwordItem(ESSENCESWORD_TOOL_MATERIAL, 25, (float) -1.8,
      (new Item.Settings().group(ItemGroup.COMBAT))) {
    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                              TooltipContext context) {
      tooltip.add(new LiteralText("A sword from some fallen god"));
      tooltip.add(new LiteralText("use at your own risk"));


    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

      int radio = 5;
      int distance = 5;
      Vec3d playerDirection = user.getRotationVector().normalize();
      Vec3d center = user.getPos().add(playerDirection.multiply(distance));
      BlockPos blockPos = new BlockPos(center.getX(), center.getY(), center.getZ());
      System.out.println("Despues del add " + center);
      System.out.println("Soy el player direction " + playerDirection);
      if (!world.isClient()) {
       serverToClientDrawParticule(world, blockPos);
      }
      if (world.isClient) {
          //ParticleUtils.soulFlameArea(blockPos, user);
      }
      SoundEvent se = new SoundEvent(id("firespell"));
      world.playSoundFromEntity(null,user,se, SoundCategory.PLAYERS,100,100);
      List<Entity> listEntities = world.getOtherEntities(user,
          new Box(center.getX() - radio, center.getY() - radio, center.getZ() - radio,
              center.getX() + radio, center.getY() + radio, center.getZ() + radio));

      double totalCount =0;
      for (double z = center.getZ() + 5; z > center.getZ() - 5; z--) {
        for (double x = (center.getX() + 5); x > center.getX() - 5; x--) {
          for (double y = (center.getY() + 1); y > center.getY() - 1; y--) {
            totalCount++;
            if (Math.random() >= 0.99) {
              LightningEntity lightningEntity =
                  (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
              lightningEntity
                  .refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos(x, y, z)));
              //lightningEntity.applyDamageEffects();
              world.spawnEntity(lightningEntity);
            }
          }
        }
      }
      for (Entity entity : listEntities) {
        if(entity instanceof MobEntity){
          entity.setOnFireFor(5);
          entity.damage(DamageSource.explosion(user), 50);
        }
      }

      return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return false;
    }
  };
}
