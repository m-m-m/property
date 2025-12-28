package io.github.mmm.property;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.base.exception.ReadOnlyException;
import io.github.mmm.marshall.StandardFormat;
import io.github.mmm.marshall.StructuredTextFormat;
import io.github.mmm.property.factory.PropertyFactoryManager;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.Validator;
import io.github.mmm.validation.main.ValidatorMandatory;
import io.github.mmm.value.observable.ObservableEvent;
import io.github.mmm.value.observable.ObservableEventListener;
import io.github.mmm.value.observable.object.ReadableSimpleValue;

/**
 * Abstract test case for testing sub-classes of {@link Property}.
 *
 * @param <V> type of the {@link Property#get() value}.
 * @param <P> type of the {@link Property}.
 */
public abstract class PropertyTest<V, P extends Property<V>> extends Assertions {

  private static final String PROPERTY_NAME = "name123";

  private final V exampleValue;

  private final Class<V> valueClass;

  private final Class<P> propertyClass;

  private final boolean createByPropertyClass;

  /**
   * The constructor.
   *
   * @param exampleValue the example value used for testing. May not be {@code null}.
   * @param propertyClass the {@link Class} reflecting the {@link Property} to test.
   */
  public PropertyTest(V exampleValue, Class<P> propertyClass) {

    this(exampleValue, propertyClass, false);
  }

  /**
   * The constructor.
   *
   * @param exampleValue the example value used for testing. May not be {@code null}.
   * @param propertyClass the {@link Class} reflecting the {@link Property} to test.
   * @param createByPropertyClass - {@code true} to create the property to test from the given {@code propertyClass},
   *        {@code false} otherwise (to create from {@link Class} of {@code exampleValue}).
   */
  @SuppressWarnings("unchecked")
  public PropertyTest(V exampleValue, Class<P> propertyClass, boolean createByPropertyClass) {

    super();
    this.exampleValue = exampleValue;
    this.valueClass = (Class<V>) exampleValue.getClass();
    this.propertyClass = propertyClass;
    this.createByPropertyClass = createByPropertyClass;
  }

  /** Test via {@link PropertyFactoryManager#create(Class, String)}. */
  @Test
  void testPropertyFromFactory() {

    // arrange
    // act
    P property = createProperty();
    // assert
    assertThat(property).isNotNull();
    assertThat(property).isInstanceOf(this.propertyClass);
    assertThat(property.getName()).isEqualTo(PROPERTY_NAME);
    assertThat(property.getValueClass()).isAssignableFrom(this.valueClass);
    assertThat(property.get()).isNull();
    assertThat(property.getSafe()).isEqualTo(property.getFallbackSafeValue());
    P readOnly = WritableProperty.getReadOnly(property);
    assertThat(readOnly.get()).isNull();
    ChangeListener<V> listener = new ChangeListener<>();
    assertThrows(ReadOnlyException.class, () -> {
      readOnly.addListener(listener);
    });
    property.addListener(listener);
    property.set(this.exampleValue);
    ObservableEvent<V> event = listener.event;
    assertThat(property).hasToString(expectedToString(property));
    assertThat(property.get()).isSameAs(this.exampleValue);
    assertThat(property.isReadOnly()).isEqualTo(false);
    assertThat(readOnly.isReadOnly()).isEqualTo(true);
    if (readOnly.isValueMutable()) {
      verifyReadOnlyValue(readOnly);
    } else {
      assertThat(readOnly.get()).isSameAs(this.exampleValue);
    }
    assertThat(event).isNotNull();
    assertThat(event.getOldValue()).isNull();
    assertThat(event.getValue()).isSameAs(this.exampleValue);
    assertThat(event.getObservable()).isSameAs(property);
    Object eventChangeType = event.getChange();
    assertThat(eventChangeType).isNull();
    assertThrows(ReadOnlyException.class, () -> {
      readOnly.set(null);
    });
    verifyJsonMarshalling(property);
  }

  /** Test via {@link Property#validate()}. */
  @Test
  void testPropertyValidation() {

    // arrange
    P property = createProperty();
    // act
    ValidationResult result = property.validate();
    // assert
    assertThat(result.isValid()).isFalse();
    assertThat(result.getCode()).isEqualTo(Validator.ID_MANDATORY);
    assertThat(result.getMessage()).isEqualTo(ValidatorMandatory.get().validate(null, property.getName()).getMessage());
    // arrange
    property.set(this.exampleValue);
    result = property.validate();
    // act
    result = property.validate();
    assertThat(result.isValid()).isTrue();
    assertThat(result.getCode()).isNull();
    assertThat(result.getMessage()).isEqualTo("Valid");
  }

  private P createProperty() {

    PropertyMetadata<V> metadata = PropertyMetadata.of(null, ValidatorMandatory.get());
    return createProperty(metadata);
  }

  private P createProperty(PropertyMetadata<V> metadata) {

    PropertyFactoryManager propertyFactoryManager = PropertyFactoryManager.get();
    Class<P> propertyType = null;
    if (this.createByPropertyClass) {
      propertyType = this.propertyClass;
    }
    P property = propertyFactoryManager.create(propertyType, this.valueClass, PROPERTY_NAME, metadata);
    assertThat(property.getMetadata()).isSameAs(metadata);
    return property;
  }

  private void verifyJsonMarshalling(P property) {

    // arrange
    V propertyValue = property.get();
    StructuredTextFormat jsonFormat = StandardFormat.json();
    // act
    String json = jsonFormat.write(property);
    P property2 = createProperty();
    assertThat(property.isEqual(property2)).isFalse();
    jsonFormat.read(json, property2);
    // assert
    // we have mapped our property to JSON and verify that we have entirely recreated it from the JSON string.
    assertThat(property2.get()).isNotNull().isEqualTo(propertyValue);
    assertThat(property2.isEqual(property)).isTrue();
    if (isJsonEqualToString() && property instanceof ReadableSimpleValue simple) {
      assertThat(json).contains(simple.getAsString());
    }

    // test that readValue(StructuredReader) has no side-effects

    // act
    property2 = createProperty();
    ChangeListener<? super V> listener = new ChangeListener<>();
    property2.addListener(listener);
    V value = property2.readValue(jsonFormat.reader(json));
    // assert
    assertThat(value).isNotNull().isEqualTo(propertyValue);
    assertThat(property2.get()).isNull();
    assertThat(listener.event).isNull();
  }

  /**
   * @return {@code true} if JSON value is the {@link ReadableSimpleValue#getAsString() string representation}.
   */
  protected boolean isJsonEqualToString() {

    return ReadableSimpleValue.class.isAssignableFrom(this.propertyClass);
  }

  /**
   * Verifies the {@link WritableProperty#get() value} of a {@link WritableProperty#getReadOnly() read-only}
   * {@link Property} pointing to the example value. This method has to be overridden for tests on
   * {@link Property#isValueMutable() mutable values}.
   *
   * @param readOnly the {@link WritableProperty#getReadOnly() read-only property view}.
   */
  protected void verifyReadOnlyValue(P readOnly) {

    throw new IllegalStateException("Property has mutable value (" + readOnly.getValueClass().getName() + ") but test ("
        + getClass().getSimpleName() + ") does not override verifyReadOnlyValue!");
  }

  private String expectedToString(WritableProperty<V> property) {

    String valueAsString = expectedPropertyValueToString(property);
    return property.getClass().getSimpleName() + "[" + PROPERTY_NAME + "=" + valueAsString + "]";
  }

  /**
   * @param property the {@link WritableProperty} to test.
   * @return the {@link Object#toString() toString()} representation of the {@link WritableProperty#get() property
   *         value} for the expected {@link WritableProperty#toString() toString()} representation of the
   *         {@link WritableProperty}.
   */
  protected String expectedPropertyValueToString(WritableProperty<V> property) {

    return property.get().toString();
  }

  private static class ChangeListener<V> implements ObservableEventListener<V> {

    private ObservableEvent<V> event;

    @Override
    public void onEvent(ObservableEvent<V> changeEvent) {

      assert (this.event == null);
      this.event = changeEvent;
    }

  }

}
