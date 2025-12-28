/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Test of {@link StringListProperty}.
 */
class StringListPropertyTest extends StringCollectionPropertyTest<StringListProperty> {

  /**
   * The constructor.
   */
  public StringListPropertyTest() {

    super(StringListProperty.class);
  }

  @Override
  protected StringListProperty createEmpty() {

    return new StringListProperty("List");
  }

  /** Test of {@link StringListProperty#contains(String)}. */
  @Test
  void testContains() {

    // arrange
    StringListProperty property = createEmpty();
    // act
    property.set("|a|bc|d|");
    // assert
    assertThat(property.contains("a")).isTrue();
    assertThat(property.contains("b")).isFalse();
    assertThat(property.contains("bc")).isTrue();
    assertThat(property.contains("d")).isTrue();
    assertThat(property.contains("e")).isFalse();
  }

  /** Test of {@link StringListProperty#contains(String)} with {@code null} and empty value. */
  @Test
  void testContainsEmpty() {

    // arrange
    StringListProperty property = createEmpty();
    // assert
    assertThat(property.contains("a")).isFalse();
    // act
    property.set("");
    // assert
    assertThat(property.contains("a")).isFalse();
  }

  /** Test of {@link StringListProperty#getAsList()}. */
  @Test
  void testGetValueAsList() {

    StringListProperty property = createEmpty();
    property.set("|a|bc|d|");
    assertThat(property.getAsList()).containsExactly("a", "bc", "d");
  }

  /** Test of {@link StringListProperty#set(java.util.Collection)}. */
  @Test
  void testSetValueAsCollection() {

    StringListProperty property = createEmpty();
    property.set(Arrays.asList("a", "bc", "d"));
    assertThat(property.getValue()).isEqualTo("|a|bc|d|");
  }

  /** Test of {@link StringListProperty#getCsv(String, boolean)}. */
  @Test
  void testGetValueAsCsv() {

    StringListProperty property = createEmpty();
    property.set("|a|bc|d|");
    assertThat(property.getCsv(", ", false)).isEqualTo("a, bc, d");
    assertThat(property.getCsv("|", true)).isEqualTo(property.get());
    assertThat(property.getCsv("|", false)).isEqualTo("a|bc|d");
  }

  /** Test of {@link StringListProperty#setCsv(String, String, boolean, boolean)}. */
  @Test
  void testSetValueAsCsv() {

    StringListProperty property = createEmpty();
    property.setCsv("a, bc, d", ", ", false);
    assertThat(property.getValue()).isEqualTo("|a|bc|d|");
    property.setCsv("a, bc, d", ",", false, false);
    assertThat(property.getValue()).isEqualTo("|a| bc| d|");
  }

  /** Test of {@link StringListProperty#add(String)}. */
  @Test
  void testAdd() {

    StringListProperty property = createEmpty();
    assertThat(property.add("a")).isTrue();
    assertThat(property.add("bc")).isTrue();
    assertThat(property.add("d")).isTrue();
    assertThat(property.getValue()).isEqualTo("|a|bc|d|");
    assertThat(property.add("b")).isTrue();
    assertThat(property.getValue()).isEqualTo("|a|bc|d|b|");
    assertThat(property.add("a")).isTrue();
    assertThat(property.getValue()).isEqualTo("|a|bc|d|b|a|");
  }

  /** Test of {@link StringListProperty#remove(String)}. */
  @Test
  void testRemove() {

    StringListProperty property = createEmpty();
    property.set("|a|bc|d|");
    assertThat(property.remove("b")).isFalse();
    assertThat(property.getValue()).isEqualTo("|a|bc|d|");
    assertThat(property.remove("a")).isTrue();
    assertThat(property.getValue()).isEqualTo("|bc|d|");
    assertThat(property.remove("d")).isTrue();
    assertThat(property.getValue()).isEqualTo("|bc|");
    assertThat(property.remove("e")).isFalse();
    assertThat(property.remove("bc")).isTrue();
    assertThat(property.getAsList()).isEmpty();
  }

  /** Test of {@link StringListProperty#add(String) adding} empty {@link String}s. */
  @Test
  void testAddEmptyStrings() {

    StringListProperty property = createEmpty();
    assertThat(property.get()).isNull();
    assertThat(property.add("")).isTrue();
    assertThat(property.get()).isEqualTo("||");
    assertThat(property.add("")).isTrue();
    assertThat(property.get()).isEqualTo("|||");
  }

  /** Test of {@link StringListProperty#set(String)} with two empty {@link String}s. */
  @Test
  void testSetEmptyStrings() {

    // arrange
    StringListProperty property = createEmpty();
    // act
    property.set("|||");
    // assert
    assertThat(property.getAsList()).containsExactly("", "");
    assertThat(property.getCsv(",", false)).isEqualTo(",");
  }

}
