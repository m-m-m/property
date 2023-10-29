/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Test of {@link StringListCsvProperty}.
 */
public class StringListCsvPropertyTest extends StringCollectionPropertyTest {

  @Override
  protected StringListCsvProperty createEmpty() {

    return new StringListCsvProperty("List");
  }

  /** Test of {@link StringListCsvProperty#contains(String)}. */
  @Test
  public void testContains() {

    // arrange
    StringListCsvProperty property = createEmpty();
    // act
    property.set("a;bc;d");
    // assert
    assertThat(property.contains("a")).isTrue();
    assertThat(property.contains("b")).isFalse();
    assertThat(property.contains("bc")).isTrue();
    assertThat(property.contains("d")).isTrue();
    assertThat(property.contains("e")).isFalse();
  }

  /** Test of {@link StringListCsvProperty#contains(String)} with {@code null} and empty value. */
  @Test
  public void testContainsEmpty() {

    // arrange
    StringListCsvProperty property = createEmpty();
    // assert
    assertThat(property.contains("a")).isFalse();
    // act
    property.set("");
    // assert
    assertThat(property.contains("a")).isFalse();
  }

  /** Test of {@link StringListCsvProperty#getAsList()}. */
  @Test
  public void testGetValueAsList() {

    StringListCsvProperty property = createEmpty();
    property.set("a;bc;d");
    assertThat(property.getAsList()).containsExactly("a", "bc", "d");
  }

  /** Test of {@link StringListCsvProperty#set(java.util.Collection)}. */
  @Test
  public void testSetValueAsCollection() {

    StringListCsvProperty property = createEmpty();
    property.set(Arrays.asList("a", "bc", "d"));
    assertThat(property.getValue()).isEqualTo("a;bc;d");
  }

  /** Test of {@link StringListCsvProperty#getCsv(String, boolean)}. */
  @Test
  public void testGetValueAsCsv() {

    StringListCsvProperty property = createEmpty();
    property.set("a;bc;d");
    assertThat(property.getCsv(", ", false)).isEqualTo("a, bc, d");
    assertThat(property.getCsv(";", false)).isEqualTo(property.get());
    assertThat(property.getCsv(";", true)).isEqualTo(";a;bc;d;");
  }

  /** Test of {@link StringListCsvProperty#setCsv(String, String, boolean, boolean)}. */
  @Test
  public void testSetValueAsCsv() {

    StringListCsvProperty property = createEmpty();
    property.setCsv("a, bc, d", ", ", false);
    assertThat(property.getValue()).isEqualTo("a;bc;d");
    property.setCsv("a, bc, d", ",", false, false);
    assertThat(property.getValue()).isEqualTo("a; bc; d");
  }

  /** Test of {@link StringListCsvProperty#add(String)}. */
  @Test
  public void testAdd() {

    StringListCsvProperty property = createEmpty();
    assertThat(property.add("a")).isTrue();
    assertThat(property.add("bc")).isTrue();
    assertThat(property.add("d")).isTrue();
    assertThat(property.getValue()).isEqualTo("a;bc;d");
    assertThat(property.add("b")).isTrue();
    assertThat(property.getValue()).isEqualTo("a;bc;d;b");
    assertThat(property.add("a")).isTrue();
    assertThat(property.getValue()).isEqualTo("a;bc;d;b;a");
  }

  /** Test of {@link StringListCsvProperty#remove(String)}. */
  @Test
  public void testRemove() {

    StringListCsvProperty property = createEmpty();
    property.set("a;bc;d");
    assertThat(property.remove("b")).isFalse();
    assertThat(property.getValue()).isEqualTo("a;bc;d");
    assertThat(property.remove("a")).isTrue();
    assertThat(property.getValue()).isEqualTo("bc;d");
    assertThat(property.remove("d")).isTrue();
    assertThat(property.getValue()).isEqualTo("bc");
    assertThat(property.remove("e")).isFalse();
    assertThat(property.remove("bc")).isTrue();
    assertThat(property.getValue()).isNull();
    assertThat(property.getAsList()).isEmpty();
  }

  /** Test of {@link StringListCsvProperty#add(String) adding} empty {@link String}s. */
  @Test
  public void testAddEmptyStrings() {

    StringListCsvProperty property = createEmpty();
    assertThat(property.get()).isNull();
    assertThat(property.add("")).isTrue();
    assertThat(property.get()).isEmpty();
    assertThat(property.add("")).isTrue();
    assertThat(property.get()).isEqualTo(";");
    assertThat(property.add("")).isTrue();
    assertThat(property.get()).isEqualTo(";;");
  }

}
