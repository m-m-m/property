package io.github.mmm.property.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.marshall.JsonFormat;
import io.github.mmm.marshall.MarshallingConfig;

/**
 * Abstract base class for tests of {@link StringCollectionProperty}.
 */
public abstract class StringCollectionPropertyTest extends Assertions {

  /**
   * @return a new and empty instance of the {@link StringCollectionProperty} to test.
   */
  protected abstract StringCollectionProperty createEmpty();

  /**
   * Test {@link StringCollectionProperty#remove(String)} with {@code null}.
   */
  @Test
  public void testRemoveNull() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    boolean removed = property.remove(null);
    // assert
    assertThat(removed).isFalse();
    assertThat(property.get()).isNull();
  }

  /**
   * Test {@link StringCollectionProperty#remove(String)} with the empty {@link String}.
   */
  @Test
  public void testRemoveEmptyString() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    boolean removed = property.remove("");
    // assert
    assertThat(removed).isFalse();
    assertThat(property.get()).isNull();
  }

  /** Test of {@link StringCollectionProperty#contains(String)}. */
  @Test
  public void testContainsNull() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    boolean contains = property.contains(null);
    // assert
    assertThat(contains).isFalse();
  }

  /** Test of {@link StringCollectionProperty#contains(String)}. */
  @Test
  public void testContainsEmptyString() {

    // arrange
    StringCollectionProperty property = createEmpty();
    boolean[] booleans = { false, true };
    for (boolean enclosed : booleans) {
      // act
      property.setCsv(",,,", ",", enclosed);
      // assert
      assertThat(property.contains("")).isTrue();
    }
  }

  /**
   * Test {@link StringCollectionProperty#getCsv(String, boolean)} when no value is set.
   */
  @Test
  public void testGetAsCsvNoElement() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    String csv = property.getCsv("$§", false);
    // assert
    assertThat(csv).isNull();
    assertThat(property.getCsv("$§", true)).isNull();
  }

  /**
   * Test {@link StringCollectionProperty#getCsv(String, boolean)} after a single element was added.
   */
  @Test
  public void testGetAsCsvSingleElement() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.add("single value");
    // assert
    assertThat(property.getCsv("$§", false)).isEqualTo("single value");
    assertThat(property.getCsv("$§", true)).isEqualTo("$§single value$§");
  }

  /**
   * Test {@link StringCollectionProperty#getCsv(String, boolean)} after multiple elements have been added.
   */
  @Test
  public void testGetAsCsvMultipleElements() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.add("one");
    property.add("two");
    property.add("three");
    // assert
    assertThat(property.getCsv("$§", false)).isEqualTo("one$§two$§three");
    assertThat(property.getCsv("$§", true)).isEqualTo("$§one$§two$§three$§");
    assertThat(property.getCsv("|", false)).isEqualTo("one|two|three");
    assertThat(property.getCsv(";", true)).isEqualTo(";one;two;three;");
    assertThat(property.getCsv(property.getSeparator(), property.isEncloseWithSeparator())).isEqualTo(property.get());
  }

  /**
   * Test {@link StringCollectionProperty#setCsv(String, String, boolean, boolean)} with null.
   */
  @Test
  public void testSetCsvNull() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.setCsv(null, ";", false, false);
    // assert
    assertThat(property.get()).isNull();
  }

  /**
   * Test {@link StringCollectionProperty#setCsv(String, String, boolean, boolean)} with empty {@link String}.
   */
  @Test
  public void testSetCsvEmptyString() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.setCsv("", ";", false, false);
    // assert
    verifyContainsOnlyEmptyString(property);
  }

  private void verifyContainsOnlyEmptyString(StringCollectionProperty property) {

    assertThat(property.collect(new ArrayList<>())).containsExactly("");
    if (property.isEncloseWithSeparator()) {
      assertThat(property.get()).isEqualTo(property.getSeparator() + property.getSeparator());
    } else {
      assertThat(property.get()).isEmpty();
    }
  }

  /**
   * Test {@link StringCollectionProperty#setCsv(String, String, boolean, boolean)} with multiple elements.
   */
  @Test
  public void testSetCsvMultipleElements() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.setCsv("hit ; pop; partyhit  ;superhits", ";", false, true);
    // assert
    assertThat(property.getCsv("$§", false)).isEqualTo("hit$§pop$§partyhit$§superhits");
    assertThat(property.getCsv("$§", true)).isEqualTo("$§hit$§pop$§partyhit$§superhits$§");
  }

  /**
   * Test {@link StringCollectionProperty#setCsv(String, String, boolean, boolean)} with a single element.
   */
  @Test
  public void testSetCsvSingleElement() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.setCsv("hit ", ";", false, false);
    // assert
    assertThat(property.getCsv("$§", false)).isEqualTo("hit ");
    assertThat(property.getCsv("$§", true)).isEqualTo("$§hit $§");
  }

  /**
   * Test {@link StringCollectionProperty#set(java.util.Collection)}.
   */
  @Test
  public void testSetCollection() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.set(List.of("one ", " two", " three "));
    // assert
    assertThat(property.getCsv(",", false)).isEqualTo("one , two, three ");
  }

  /**
   * Test {@link StringCollectionProperty#set(java.util.Collection)} with empty {@link Collection}.
   */
  @Test
  public void testSetCollectionEmpty() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.set(List.of());
    // assert
    assertThat(property.getCsv(",", false)).isNull();
  }

  /**
   * Test {@link StringCollectionProperty#set(java.util.Collection)} with {@code null}.
   */
  @Test
  public void testSetCollectionNull() {

    // arrange
    StringCollectionProperty property = createEmpty();
    Collection<String> collection = null;
    // act
    property.set(collection);
    // assert
    assertThat(property.getCsv(",", false)).isEqualTo(null);
  }

  /**
   * Test of {@link StringCollectionProperty#read(io.github.mmm.marshall.StructuredReader)} as flat JSON {@link String}.
   */
  @Test
  public void testReadJsonString() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    JsonFormat.of().read("\"" + value + "\"", property);
    // assert
    assertThat(property.get()).isEqualTo(value);
  }

  private String fill4Tags() {

    StringCollectionProperty property = createEmpty();
    property.setCsv("hit;pop;partyhit;superhits", ";", false, false);
    String value = property.get();
    assertThat(value).contains("hit", "pop", "partyhit", "superhits");
    return value;
  }

  /**
   * Test of {@link StringCollectionProperty#read(io.github.mmm.marshall.StructuredReader)} as JSON array of
   * {@link String}s.
   */
  @Test
  public void testReadJsonArray() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    JsonFormat.of().read("[\"hit\",\"pop\",\"partyhit\",\"superhits\"]", property);
    // assert
    assertThat(property.get()).isEqualTo(value);
  }

  /**
   * Test of {@link StringCollectionProperty#read(io.github.mmm.marshall.StructuredReader)} from a JSON array of
   * {@link String}s.
   */
  @Test
  public void testWriteArray() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    property.set(value);
    String json = JsonFormat.of(MarshallingConfig.NO_INDENTATION).write(property);
    // assert
    assertThat(json).isEqualTo("[\"hit\",\"pop\",\"partyhit\",\"superhits\"]");
  }

}
