package net.eriagacha.controller;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.File;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.EriaGachaMain;
import net.eriagacha.models.GachaTelemetryModel;
import net.eriagacha.repository.GachaTelemetryRepository;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import reactor.core.publisher.Flux;


@Log4j2
public class GachaTelemetryController {


  public GachaTelemetryController() {
  }


  public static void InsertTelemetry(String playerName, String rewardObtained) {
    GachaTelemetryRepository gr =
        EriaGachaMain.springContext.getBean(GachaTelemetryRepository.class);

    final GachaTelemetryRepository gachaTelemetryRepository;
    GachaTelemetryModel gtm = GachaTelemetryModel
        .builder()
        .user(playerName)
        .rewardObtained(rewardObtained)
        .date(String.valueOf(java.time.LocalDateTime.now()))
        .build();

    var saved = Flux
        .just(gtm)
        .map(
            name -> new GachaTelemetryModel(
                name.getUser(),
                name.getRewardObtained(),
                name.getDate()))
        .flatMap(gr::save);

      /*
      gr.deleteAll()
          .thenMany( saved)
          .thenMany( gr.findAll())
          .subscribe(log::info);
       */
    log.info("Soy información");
    saved.subscribe(log::info);
  }

  public static void selectTelemetry(CommandContext<ServerCommandSource> ctx)
      throws CommandSyntaxException {
    GachaTelemetryRepository gr =
        EriaGachaMain.springContext.getBean(GachaTelemetryRepository.class);

    gr.findByUser(ctx.getSource().getPlayer().getName().asString())
        .map(gachaTelemetry ->
        {
          ctx.getSource().sendFeedback(new
              LiteralText(
              "\n ********** "
                  + "\n Recompensa : " + gachaTelemetry.getRewardObtained()
                  + "\n Usuario : " + gachaTelemetry.getUser()
                  + "\n Fecha :" + gachaTelemetry.getDate()), false);
          return 1;
        }).subscribe();


    //gr.findByUser(ctx.getSource().getPlayer().getName().asString()).subscribe(log::info);
  }


  public static void registerTelemetry(GachaTelemetryModel gtm) throws IOException {

    File file = new File(gtm.getUser() + ".json");
    if (!file.exists()) {
      file.createNewFile();
      System.out.println("No existia");
      writeNewTelemetryLine(file, gtm);
    } else {
      System.out.println("Si existia");
      writeNewTelemetryLine(file, gtm);
    }
  }

  private static void writeNewTelemetryLine(File file, GachaTelemetryModel gtm) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    mapper.writeValue(file.getAbsoluteFile(), gtm);
    GachaTelemetryModel finalModel = mapper.readValue(file, GachaTelemetryModel.class);

  }
}
