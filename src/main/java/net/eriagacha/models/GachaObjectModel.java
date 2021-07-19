package net.eriagacha.models;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import net.minecraft.server.command.ServerCommandSource;

@Data
@SuperBuilder
@Log4j2
public abstract class GachaObjectModel {
  private String rewardName;
  private double weight;

  public abstract void reward(CommandContext<ServerCommandSource> ctx)
      throws CommandSyntaxException;
}
