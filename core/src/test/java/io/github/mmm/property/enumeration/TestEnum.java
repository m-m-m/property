package io.github.mmm.property.enumeration;

enum TestEnum {

  FOO("foo1"),

  BAR("bar2");

  private final String title;

  private TestEnum(String title) {

    this.title = title;
  }

  @Override
  public String toString() {

    return this.title;
  }
}