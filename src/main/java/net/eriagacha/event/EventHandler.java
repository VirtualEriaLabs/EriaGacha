package net.eriagacha.event;

public class EventHandler {

  private EventHandler() {
  }

  public static EriaEvent GACHA_ROLL_EVENT;

  public static void init() {
    GACHA_ROLL_EVENT = GachaRollEvent.builder().build();
  }

}
