package net.eriagacha.utils;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lombok.extern.log4j.Log4j2;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

@Log4j2
public class PlayerHelper {
  public static ServerPlayerEntity getPlayer(CommandContext<ServerCommandSource> ctx)
      throws CommandSyntaxException {
    ServerPlayerEntity self = null;
    try {
      return ctx.getSource().getPlayer();
    } catch (CommandSyntaxException e) {
      log.error("Is not a player.");
      throw new SimpleCommandExceptionType(
          new TranslatableText("Player reference is not a real player")).create();
    }
  }
}
