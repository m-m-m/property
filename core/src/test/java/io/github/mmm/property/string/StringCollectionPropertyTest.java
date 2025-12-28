package io.github.mmm.property.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.mmm.marshall.MarshallingConfig;
import io.github.mmm.marshall.StandardFormat;
import io.github.mmm.property.PropertyTest;

/**
 * Abstract base class for tests of {@link StringCollectionProperty}.
 *
 * @param <P> type of the {@link StringCollectionProperty}.
 */
public abstract class StringCollectionPropertyTest<P extends StringCollectionProperty> extends PropertyTest<String, P> {

  /**
   * The constructor.
   *
   * @param propertyClass the {@link StringCollectionProperty} {@link Class}.
   */
  protected StringCollectionPropertyTest(Class<P> propertyClass) {

    super(exampleValue(propertyClass), propertyClass, true);
  }

  private static String exampleValue(Class<?> propertyClass) {

    if (propertyClass.getSimpleName().contains("Csv")) {
      return "a;bc;b;aa";
    } else {
      return "|a|bc|b|aa|";
    }
  }

  @Override
  protected boolean isJsonEqualToString() {

    return false;
  }

  /**
   * @return a new and empty instance of the {@link StringCollectionProperty} to test.
   */
  protected abstract P createEmpty();

  /**
   * Test {@link StringCollectionProperty#remove(String)} with {@code null}.
   */
  @Test
  void testRemoveNull() {

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
  void testRemoveEmptyString() {

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
  void testContainsNull() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    boolean contains = property.contains(null);
    // assert
    assertThat(contains).isFalse();
  }

  /** Test of {@link StringCollectionProperty#contains(String)}. */
  @Test
  void testContainsEmptyString() {

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
  void testGetAsCsvNoElement() {

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
  void testGetAsCsvSingleElement() {

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
  void testGetAsCsvMultipleElements() {

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
  void testSetCsvNull() {

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
  void testSetCsvEmptyString() {

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
  void testSetCsvMultipleElements() {

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
  void testSetCsvSingleElement() {

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
  void testSetCollection() {

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
  void testSetCollectionEmpty() {

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
  void testSetCollectionNull() {

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
  void testReadJsonString() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    StandardFormat.json().read("\"" + value + "\"", property);
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
  void testReadJsonArray() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    StandardFormat.json().read("[\"hit\",\"pop\",\"partyhit\",\"superhits\"]", property);
    // assert
    assertThat(property.get()).isEqualTo(value);
  }

  /**
   * Test of {@link StringCollectionProperty#read(io.github.mmm.marshall.StructuredReader)} from a JSON array of
   * {@link String}s.
   */
  @Test
  void testWriteArray() {

    // arrange
    String value = fill4Tags();
    StringCollectionProperty property = createEmpty();
    // act
    property.set(value);
    String json = StandardFormat.json(MarshallingConfig.NO_INDENTATION).write(property);
    // assert
    assertThat(json).isEqualTo("[\"hit\",\"pop\",\"partyhit\",\"superhits\"]");
  }

  /**
   * Test {@link StringCollectionProperty#iterator()} after multiple elements have been added.
   */
  @Test
  void testIterable() {

    // arrange
    StringCollectionProperty property = createEmpty();
    // act
    property.add("one");
    property.add("two");
    property.add("three");
    // assert
    Iterable<String> iterable = property;
    assertThat(iterable).containsExactly("one", "two", "three");
  }
}
