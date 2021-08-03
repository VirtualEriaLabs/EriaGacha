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


  private Commissions() {}


  //TODO: CREATE A CRUD FOR COMMISSIONS
  public static void getCommision(CommandContext<ServerCommandSource> ctx)
      throws IOException, CommandSyntaxException {

    File file = new File(ctx.getSource().getName() + ".json");
    if (!file.exists()){
      file.createNewFile();
      System.out.println("No existia");
      getMissions(file);
    }
    else {
      System.out.println("Si existia");
      getMissions(file);
    }
  }




  public static void getMissions(File file) throws IOException {

    //TODO CHECK IF THE USER HAVE AN ONGOING COMMISSION
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    CommissionModel cm = CommissionsUtils.getItemMission();
    mapper.writeValue(file.getAbsoluteFile(), cm);
    CommissionModelItem finalModel = mapper.readValue(file, CommissionModelItem.class);
    if (finalModel instanceof CommissionModelItem)
    {
      return;
    }
  }


}
