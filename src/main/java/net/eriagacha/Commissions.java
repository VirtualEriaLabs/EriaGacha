package net.eriagacha;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.File;
import java.io.IOException;
import net.eriagacha.models.CommissionModelTest;
import net.eriagacha.utils.CommissionsUtils;
import net.minecraft.server.command.ServerCommandSource;

public class Commissions {


  //TODO: INVESTIGATE GSON to create, write, edit and remove a JSON File and its components
  private class Person {
    public String name;

    public Person(String name) {
      this.name = name;
    }
  }

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
    System.out.println("-----1-----");
    CommissionModelTest cm = CommissionsUtils.getItemMission();
    System.out.println("-----2-----");
    mapper.writeValue(file.getAbsoluteFile(), cm.getCommissionTask());
    cm.getItemNeeded().
    System.out.println("-----2.5-----");
    System.out.println(cm.getItemNeeded());
    System.out.println("-----3-----");
    CommissionModelTest finalModel = mapper.readValue(file, CommissionModelTest.class);
    System.out.println("-----DATOS-----");
    System.out.println(finalModel.getCommissionName());
    System.out.println(finalModel.getItemNeeded());
    System.out.println(finalModel.getItemQuanty());



    // Java object to JSON string, default compact-print
    //String jsonString = mapper.writeValueAsString(new CommissionModel());

    // pretty-print
    //String jsonString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Staff());
  }
}
