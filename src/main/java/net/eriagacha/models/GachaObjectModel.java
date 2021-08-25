package net.eriagacha.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

@Data
@SuperBuilder
@Log4j2
public class GachaObjectModel {
  private String rewardName;
  private double weight;

}
