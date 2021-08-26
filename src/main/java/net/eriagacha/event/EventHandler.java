package net.eriagacha.event;

public class EventHandler {

  private EventHandler() {
  }

  public static GachaRollEvent gre;

  public static void init() {
    gre = new GachaRollEvent();
  }

}
