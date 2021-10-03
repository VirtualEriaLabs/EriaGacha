package com.eriagacha.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.math.BlockPos;

public class BlockPosUtils {


  public static List<BlockPos> cubicBlockArea(int areaRadio, int farFromPlayer, BlockPos pos){

    List BlockPosList = new ArrayList();
    for (double z = pos.getZ() + areaRadio; z > pos.getZ() - areaRadio; z--) {
      for (double x = (pos.getX() + areaRadio); x > pos.getX() - areaRadio; x--) {
        for (double y = (pos.getY() + areaRadio); y > pos.getY() - areaRadio; y--) {
          BlockPosList.add(new BlockPos(x, y, z));
        }
      }
    }
    return BlockPosList;
  }

}
