package edu.baylor.ecs.ams.model;

/**
 * Possible sources where papers can be pulled from
 */
public enum Source {
  IEEE("ieee"),
  ACM("acm");

  private final String source;

  Source(final String source) {
    this.source = source;
  }

  @Override
  public String toString() {
    return source;
  }
}
