package net.eriagacha.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.eriagacha.models.GachaObjectModel;

public class WeightedRandomBag <T extends GachaObjectModel> {

  private class Entry{
    double accumulatedWeight;
    T gachaobject;
  }

  private List<Entry> entries = new ArrayList<>();
  private double accumulatedWeight;
  private Random rand = new Random();

  public void addEntry(T gachaobject, double weight) {
    accumulatedWeight += weight;
    Entry e = new Entry();
    e.gachaobject = gachaobject;
    e.accumulatedWeight = accumulatedWeight;
    entries.add(e);
  }

  public T getRandom() {
    double r = rand.nextDouble() * accumulatedWeight;

    for (Entry entry: entries) {
      if (entry.accumulatedWeight >= r) {
        return entry.gachaobject;
      }
    }
    return null; //should only happen when there are no entries
  }
}
