package com.eriagacha.item;

import static com.eriagacha.network.NetworkServer.serverToClientDrawParticule;
import static com.eriagacha.register.RegisterSound.FIRE_SPELL_SOUND;
import static com.eriagacha.utils.EntityHelper.OnEntityHitDamage;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TavinusSwordTool {

  public static final ToolMaterial TAVINUS_SWORD_TOOL_MATERIAL = new ToolMaterial() {

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
      return 12F;
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

  public static final Item INSTANCE = new SwordItem(TAVINUS_SWORD_TOOL_MATERIAL, 25, (float) -1.8,
      (new Item.Settings().group(ItemGroup.COMBAT))) {
    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                              TooltipContext context) {
      tooltip.add(new TranslatableText("text.eriagacha.tavinus_sword_description_0"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
      int radio = 5;
      int distanceFromPlayer = 5 ;
      Vec3d center = user.getPos().add(user.getRotationVector().normalize().multiply(distanceFromPlayer));
      BlockPos blockPos = new BlockPos(center.getX(), center.getY(), center.getZ());
      if (!world.isClient()) {
         world.playSoundFromEntity(null,user,FIRE_SPELL_SOUND, SoundCategory.PLAYERS,100,100);
         serverToClientDrawParticule(world, blockPos, radio, distanceFromPlayer);
      }
      List<Entity> listEntities = world.getOtherEntities(user,
          new Box(center.getX() - radio, center.getY() - radio, center.getZ() - radio,
              center.getX() + radio, center.getY() + radio, center.getZ() + radio));

      OnEntityHitDamage(listEntities, user, this.getAttackDamage()/2, true);
      return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean hasGlint(ItemStack stack) {
      return false;
    }
  };
}
