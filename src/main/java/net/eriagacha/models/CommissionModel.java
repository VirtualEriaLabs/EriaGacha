package net.eriagacha.models;


import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.log4j.Log4j2;

@Data
@SuperBuilder
@Log4j2
public class CommissionModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 8642948807010652686L;

  private String commissionName;
  private String commissionTask;
}
