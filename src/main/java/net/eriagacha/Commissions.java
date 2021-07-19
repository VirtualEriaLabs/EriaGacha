package net.eriagacha;

import com.google.gson.Gson;
import com.mojang.brigadier.context.CommandContext;
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
  {
    Gson g = new Gson();
    Person person = g.fromJson("{\"name\": \"John\"}", Person.class);
    System.out.println(person.name); //John
    System.out.println(g.toJson(person)); // {"name":"John"}
  }
}
