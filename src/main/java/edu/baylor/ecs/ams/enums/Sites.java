package edu.baylor.ecs.ams.enums;

public enum Sites {
  ieee("ieee"),
  sciencedirect("sciencedirect");

  private final String text;

  /**
   * @param text
   */
  Sites(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
