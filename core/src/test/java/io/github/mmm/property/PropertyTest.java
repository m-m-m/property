package io.github.mmm.property;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.base.exception.ReadOnlyException;
import io.github.mmm.property.factory.PropertyFactoryManager;
import io.github.mmm.value.observable.ObservableEvent;
import io.github.mmm.value.observable.ObservableEventListener;

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
  public void testFactory() {

    // arrange
    String name = PROPERTY_NAME;
    ;
    // act
    PropertyFactoryManager propertyFactoryManager = PropertyFactoryManager.get();
    Class<P> propertyType = null;
    if (this.createByPropertyClass) {
      propertyType = this.propertyClass;
    }
    WritableProperty<V> property = propertyFactoryManager.create(propertyType, this.valueClass, name);
    // assert
    assertThat(property).isNotNull();
    assertThat(property).isInstanceOf(this.propertyClass);
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.getValueClass()).isAssignableFrom(this.valueClass);
    assertThat(property.getSafe()).isNotNull();
    assertThat(property.get()).isNull();
    WritableProperty<V> readOnly = property.getReadOnly();
    assertThat(readOnly.get()).isNull();
    ChangeListener<V> listener = new ChangeListener<>();
    readOnly.addListener(listener);
    property.set(this.exampleValue);
    ObservableEvent<V> event = listener.event;
    assertThat(property).hasToString(expectedToString(property));
    assertThat(property.get()).isSameAs(this.exampleValue);
    assertThat(property.isReadOnly()).isEqualTo(false);
    assertThat(readOnly.isReadOnly()).isEqualTo(true);
    assertThat(readOnly.get()).isSameAs(this.exampleValue);
    assertThat(event).isNotNull();
    assertThat(event.getOldValue()).isNull();
    assertThat(event.getValue()).isSameAs(this.exampleValue); // TODO
    Object eventChangeType = event.getChange();
    assertThat(eventChangeType).isNull();
    assertThrows(ReadOnlyException.class, () -> {
      readOnly.set(null);
    });
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
