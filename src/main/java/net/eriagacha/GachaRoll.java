package net.eriagacha;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.eriagacha.models.GachaRewardModel;
import net.minecraft.item.ItemStack;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GachaRoll {

  private List<GachaRewardModel> gachaRewards;
  private ItemStack moneyCondition;
  private String name;
  private double weight;
  private String feebackMessage;


  public void setGachaRewards(GachaRewardModel grm) {
    this.gachaRewards.add(grm);
  }
}
