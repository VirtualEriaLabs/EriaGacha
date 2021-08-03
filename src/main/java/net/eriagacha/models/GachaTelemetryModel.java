package net.eriagacha.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.log4j.Log4j2;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Log4j2
@Jacksonized
public class GachaTelemetryModel {

  private String playerName;
  private String rewardObtained;
  private String date;


}
