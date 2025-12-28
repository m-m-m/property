package io.github.mmm.property.string;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Test of {@link StringSetCsvProperty}.
 */
class StringSetCsvPropertyTest extends StringCollectionPropertyTest<StringSetCsvProperty> {

  /**
   * The constructor.
   */
  public StringSetCsvPropertyTest() {

    super(StringSetCsvProperty.class);
  }

  @Override
  protected StringSetCsvProperty createEmpty() {

    return new StringSetCsvProperty("Set");
  }

  /**
   * Test {@link StringSetCsvProperty#remove(String)} of last tag with collision.
   */
  @Test
  void testRemoveLast() {

    // arrange
    StringSetCsvProperty tags = createEmpty();
    tags.set("pop;partyhit;superhits;hit");
    Set<String> set = tags.getAsSet();
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits", "hit");
    // act
    boolean removed = tags.remove("hit");
    // assert
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("pop;partyhit;superhits");
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits");
  }

  /**
   * Test {@link StringSetCsvProperty#remove(String)} of first tag with collision.
   */
  @Test
  void testRemoveFirst() {

    // arrange
    StringSetCsvProperty tags = createEmpty();
    tags.set("hit;pop;partyhit;superhits");
    Set<String> set = tags.getAsSet();
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits", "hit");
    // act
    boolean removed = tags.remove("hit");
    // assert
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("pop;partyhit;superhits");
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits");
  }

  /**
   * Test {@link StringSetCsvProperty#getCsv(String, boolean)}.
   */
  @Test
  void testGetAsCsv() {

    // arrange
    StringSetCsvProperty tags = createEmpty();
    tags.set("hit;pop;partyhit;superhits");
    // act
    String csv = tags.getCsv("$§", true);
    // assert
    assertThat(csv).isEqualTo("$§hit$§pop$§partyhit$§superhits$§");
  }

  /**
   * Test {@link StringSetCsvProperty#setCsv(String, String, boolean, boolean)}.
   */
  @Test
  void testSetCsv() {

    // arrange
    StringSetCsvProperty tags = createEmpty();
    // act
    tags.setCsv("$§hit $§ pop$§ partyhit  $§superhits$§", "$§", true, true);
    // assert
    assertThat(tags.get()).isEqualTo("hit;pop;partyhit;superhits");
  }

  /**
   * Test {@link StringSetCsvProperty#setCsv(String, String, boolean, boolean)} with a single element.
   */
  @Test
  void testSetCsvSingle() {

    // arrange
    StringSetCsvProperty tags = createEmpty();
    // act
    tags.setCsv("$§hit $§", "$§", true, false);
    // assert
    assertThat(tags.get()).isEqualTo("hit ");
    assertThat(tags.getAsSet()).containsExactly("hit ");
  }

  /** Test of {@link StringSetCsvProperty#contains(String)}. */
  @Test
  void testContains() {

    // arrange
    StringSetCsvProperty property = createEmpty();
    // act
    property.set("a;bc;d");
    // assert
    assertThat(property.contains("a")).isTrue();
    assertThat(property.contains("b")).isFalse();
    assertThat(property.contains("bc")).isTrue();
    assertThat(property.contains("d")).isTrue();
    assertThat(property.contains("e")).isFalse();
  }

}
