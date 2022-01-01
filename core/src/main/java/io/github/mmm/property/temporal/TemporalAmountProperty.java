/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal;

import java.time.temporal.TemporalAmount;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.value.observable.temporal.WritableTemporalAmountValue;

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
  public void write(StructuredWriter writer) {

    writer.writeValue(get());
  }

  @Override
  public void read(StructuredReader reader) {

    V value = reader.readValue(getValueClass());
    set(value);
  }

}
