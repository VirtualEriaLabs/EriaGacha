package com.eriagacha.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;


@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Log4j2
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class GachaTelemetryModel {
  @Id
  private String ID;
  private String rewardObtained;
  private String user;
  private String date;

  public GachaTelemetryModel(String rewardObtained, String user, String date) {
    this.rewardObtained = rewardObtained;
    this.user = user;
    this.date = date;
  }
}
