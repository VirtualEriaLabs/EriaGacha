package net.eriagacha.controller;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import net.eriagacha.models.GachaTelemetryModel;

public class GachaTelemetry {


  public GachaTelemetry() {
  }


  public static void registerTelemetry(GachaTelemetryModel gtm) throws IOException {

    File file = new File(gtm.getPlayerName() + ".json");
    if (!file.exists()){
      file.createNewFile();
      System.out.println("No existia");
      writeNewTelemetryLine(file, gtm);
    }
    else {
      System.out.println("Si existia");
      writeNewTelemetryLine(file, gtm);
    }
  }

  private static void writeNewTelemetryLine(File file, GachaTelemetryModel  gtm) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    mapper.writeValue(file.getAbsoluteFile(), gtm);
    GachaTelemetryModel finalModel = mapper.readValue(file, GachaTelemetryModel.class);

  }
}
