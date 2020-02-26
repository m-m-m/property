/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import java.util.function.Function;

import io.github.mmm.base.range.GenericRange;
import io.github.mmm.base.range.Range;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableRangeProperty}.
 *
 * @param <V> type of the {@link Range} value.
 * @since 1.0.0
 */
public class RangeProperty<V> extends SimpleProperty<Range<V>> implements WritableRangeProperty<V> {

  /** @see #getValueProperty() */
  private final SimpleProperty<V> valueProperty;

  private Range<V> value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public RangeProperty(String name, SimpleProperty<V> valueProperty) {

    this(name, valueProperty, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public RangeProperty(String name, SimpleProperty<V> valueProperty, PropertyMetadata<Range<V>> metadata) {

    super(name, metadata);
    this.valueProperty = valueProperty;
  }

  @Override
  protected Range<V> doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Range<V> newValue) {

    this.value = newValue;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public Range<V> parse(String valueAsString) {

    if (valueAsString == null) {
      return null;
    }
    Function<String, V> boundParser;
    if (this.valueProperty == null) {
      boundParser = (Function) (s) -> s;
    } else {
      boundParser = (s) -> this.valueProperty.parse(s);
    }
    return GenericRange.parse(valueAsString, boundParser);
  }

  /**
   * @return the {@link Property} representing the type of the values contained in the {@link Range} {@link #get()
   *         value}.
   */
  public Property<V> getValueProperty() {

    return this.valueProperty;
  }
}
