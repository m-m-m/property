package io.github.mmm.property.string;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Test of {@link StringSetProperty}.
 */
public class StringSetPropertyTest extends StringCollectionPropertyTest {

  @Override
  protected StringSetProperty createEmpty() {

    return new StringSetProperty("Set");
  }

  /**
   * Test {@link StringSetProperty#remove(String)} of last tag with collision.
   */
  @Test
  public void testRemoveLast() {

    // arrange
    StringSetProperty tags = createEmpty();
    tags.set("|pop|partyhit|superhits|hit|");
    Set<String> set = tags.getAsSet();
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits", "hit");
    // act
    boolean removed = tags.remove("hit");
    // assert
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("|pop|partyhit|superhits|");
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits");
  }

  /**
   * Test {@link StringSetProperty#remove(String)} of first tag with collision.
   */
  @Test
  public void testRemoveFirst() {

    // arrange
    StringSetProperty tags = createEmpty();
    tags.set("|hit|pop|partyhit|superhits|");
    Set<String> set = tags.getAsSet();
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits", "hit");
    // act
    boolean removed = tags.remove("hit");
    // assert
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("|pop|partyhit|superhits|");
    assertThat(set).containsExactlyInAnyOrder("pop", "partyhit", "superhits");
    assertThat(tags.remove("non-existent")).isFalse();
  }

  /**
   * Test {@link StringSetProperty#getCsv(String, boolean)}.
   */
  @Test
  public void testGetAsCsv() {

    // arrange
    StringSetProperty tags = createEmpty();
    tags.set("|hit|pop|partyhit|superhits|");
    // act
    String csv = tags.getCsv(";", false);
    // assert
    assertThat(csv).isEqualTo("hit;pop;partyhit;superhits");
  }

  /**
   * Test {@link StringSetProperty#setCsv(String, String, boolean, boolean)}.
   */
  @Test
  public void testSetCsv() {

    // arrange
    StringSetProperty tags = createEmpty();
    // act
    tags.setCsv("hit ; pop; partyhit  ;superhits", ";", false, true);
    // assert
    assertThat(tags.get()).isEqualTo("|hit|pop|partyhit|superhits|");
  }

  /**
   * Test {@link StringSetProperty#setCsv(String, String, boolean, boolean)} with a single element.
   */
  @Test
  public void testSetCsvSingle() {

    // arrange
    StringSetProperty tags = createEmpty();
    // act
    tags.setCsv("hit ", ";", false, false);
    // assert
    assertThat(tags.get()).isEqualTo("|hit |");
    assertThat(tags.getAsSet()).containsExactly("hit ");
  }

  /** Test of {@link StringSetProperty#contains(String)}. */
  @Test
  public void testContains() {

    // arrange
    StringSetProperty property = createEmpty();
    // act
    property.set("|a|bc|d|");
    // assert
    assertThat(property.contains("a")).isTrue();
    assertThat(property.contains("b")).isFalse();
    assertThat(property.contains("bc")).isTrue();
    assertThat(property.contains("d")).isTrue();
    assertThat(property.contains("e")).isFalse();
  }

}
