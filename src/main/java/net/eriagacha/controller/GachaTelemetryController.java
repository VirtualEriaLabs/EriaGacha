package net.eriagacha.controller;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.eriagacha.EriaGachaMain;
import net.eriagacha.models.GachaTelemetryModel;
import net.eriagacha.repository.GachaTelemetryRepository;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import reactor.core.publisher.Flux;


@Log4j2
//@Environment(EnvType.SERVER) COMENTARIO IMPORTANTE
public class GachaTelemetryController {


  private GachaTelemetryController() {}


  public static void InsertTelemetry(String playerName, String rewardObtained) {
    GachaTelemetryRepository gr =
        EriaGachaMain.springContext.getBean(GachaTelemetryRepository.class);

    /*
    Hooks.onErrorDropped(error -> {
      log.info("Soy un error");
    });
    */
    final GachaTelemetryRepository gachaTelemetryRepository;
    GachaTelemetryModel gtm = GachaTelemetryModel
        .builder()
        .user(playerName)
        .rewardObtained(rewardObtained)
        .date(String.valueOf(java.time.LocalDateTime.now()))
        .build();

    log.info(gtm.getUser() + gtm.getDate() + gtm.getRewardObtained());


    var saved = Flux
        .just(gtm)
        .map(
            name -> new GachaTelemetryModel(
                name.getUser(),
                name.getRewardObtained(),
                name.getDate()))
        .flatMap(gr::save);

    try {
      saved.subscribe();
    }catch (Exception e){
      log.error("Erorr de GTC" + e.getMessage());
    }

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
  }
}
