package com.eriagacha.telemetry;

import com.eriagacha.EriaGachaServerMain;
import com.eriagacha.models.GachaTelemetryModel;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import reactor.core.publisher.Flux;


@Log4j2
@Environment(EnvType.SERVER)
public class GachaTelemetryController {

  private GachaTelemetryController() {
  }

  public static void insertTelemetry(String playerName, String rewardObtained) {
    GachaTelemetryRepository gr =
        EriaGachaServerMain.springContext.getBean(GachaTelemetryRepository.class);

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

    saved.subscribe();

  }

  public static void selectTelemetry(ServerPlayerEntity player)
      throws CommandSyntaxException {
    GachaTelemetryRepository gr =
        EriaGachaServerMain.springContext.getBean(GachaTelemetryRepository.class);

    gr.findByUser(player.getName().asString())
        .map(gachaTelemetry ->
        {
          player.sendMessage(new
              LiteralText(
              "\n ********** "
                  + "\n Recompensa : " + gachaTelemetry.getRewardObtained()
                  + "\n Usuario : " + gachaTelemetry.getUser()
                  + "\n Fecha :" + gachaTelemetry.getDate()), false

          );
          return 1;
        }).subscribe();
  }
}
