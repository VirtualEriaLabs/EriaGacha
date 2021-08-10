package net.eriagacha.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.eriagacha.models.GachaObjectModel;

public class WeightedRandomBag<T extends GachaObjectModel> {

  private final List<Entry> entries = new ArrayList<>();
  private final Random rand = new Random();
  private double accumulatedWeight;

  public void addEntry(T gachaObject) {
    this.addEntry(gachaObject, gachaObject.getWeight());
  }

  public void addEntry(T gachaObject, double weight) {
    accumulatedWeight += weight;
    Entry e = new Entry();
    e.gachaobject = gachaObject;
    e.accumulatedWeight = accumulatedWeight;
    entries.add(e);
  }

  public T getRandom() {
    double r = rand.nextDouble() * accumulatedWeight;

    for (Entry entry : entries) {
      if (entry.accumulatedWeight >= r) {
        return entry.gachaobject;
      }
    }
    return null; //should only happen when there are no entries
  }

  private class Entry {
    double accumulatedWeight;
    T gachaobject;
  }
}
