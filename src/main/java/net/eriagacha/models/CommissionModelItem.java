package net.eriagacha.models;


import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@SuperBuilder
public class CommissionModel
{

  public String commissionName;
  public String commissionTask;
}
