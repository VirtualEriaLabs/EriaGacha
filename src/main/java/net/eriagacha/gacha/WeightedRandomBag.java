package net.eriagacha.gacha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WeightedRandomBag<T extends GachaRoll> {

  private final List<Entry> entries = new ArrayList<>();
  private final Random rand = new Random();
  private double accumulatedWeight;

  public void addEntry(T gachaObject) {
    this.addEntry(gachaObject, gachaObject.getWeight());
  }

  public void addEntries(List<T> gachaRolls) {
    gachaRolls.stream().forEach(roll -> this.addEntry(roll));
  }

  public void addEntry(T gachaObject, double weight) {
    this.accumulatedWeight += weight;
    Entry e = new Entry();
    e.gachaobject = gachaObject;
    e.accumulatedWeight = this.accumulatedWeight;
    log.error("New Entry Name : {} \n New Entry Weight : {}", e.gachaobject.toString(),
        e.accumulatedWeight);
    this.entries.add(e);
  }

  public T getRandom() {
    double r = this.rand.nextDouble() * this.accumulatedWeight;

    log.error("rand.nextDouble : {} \n accumulatedWeight : {} \n ", this.rand.nextDouble(),
        this.accumulatedWeight);

    log.error("r value : {}", r);
    for (Entry entry : this.entries) {
      log.error("Entry accumulated Weight : {}", entry.accumulatedWeight);
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
