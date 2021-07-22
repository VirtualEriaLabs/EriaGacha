package net.eriagacha;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.File;
import java.io.IOException;
import net.eriagacha.models.CommissionModel;
import net.eriagacha.models.CommissionModelItem;
import net.eriagacha.utils.CommissionsUtils;
import net.minecraft.server.command.ServerCommandSource;

public class Commissions {


  //TODO: INVESTIGATE GSON to create, write, edit and remove a JSON File and its components
  public static void getCommision(CommandContext<ServerCommandSource> ctx)
      throws IOException, CommandSyntaxException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    File file = new File(ctx.getSource().getName() + ".json");


    if (!file.exists()){
      file.createNewFile();
      System.out.println("No existia");
    }
    else {
      System.out.println("Si existia");
    }

    CommissionModel cm = CommissionsUtils.getItemMission();
    mapper.writeValue(file.getAbsoluteFile(), cm);

    CommissionModel finalModel = mapper.readValue(file, CommissionModelItem.class);



  }
}
