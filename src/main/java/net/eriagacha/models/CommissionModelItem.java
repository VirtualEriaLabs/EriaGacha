package net.eriagacha.models;


import java.io.Serial;
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
public class CommissionModelItem extends CommissionModel {

  @Serial
  private static final long serialVersionUID = 8642948801310652686L;

  private int itemNeeded;
  private int itemQuantity;
}
