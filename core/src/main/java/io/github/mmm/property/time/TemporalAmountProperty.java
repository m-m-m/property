/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time;

import java.time.temporal.TemporalAmount;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.value.observable.time.WritableTemporalAmountValue;

/**
 * {@link SimpleProperty} for {@link TemporalAmount} {@link #get() value}.
 *
 * @param <V> type of {@link #get() value}.
 * @since 1.0.0
 */
public abstract class TemporalAmountProperty<V extends TemporalAmount> extends SimpleProperty<V>
    implements WritableTemporalAmountValue<V> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public TemporalAmountProperty(String name, PropertyMetadata<V> metadata) {

    super(name, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public TemporalAmountProperty(String name) {

    super(name);
  }

  @Override
  protected V readValue(StructuredReader reader, boolean apply) {

    V value = reader.readValue(getValueClass());
    if (apply) {
      set(value);
    }
    return value;
  }

  @Override
  public void writeValue(StructuredWriter writer, V value) {

    writer.writeValue(value);
  }

}
