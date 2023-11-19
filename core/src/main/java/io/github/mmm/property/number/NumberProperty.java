/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableNumberProperty}.
 *
 * @param <N> type of the numeric {@link #get() value}.
 * @since 1.0.0
 */
public abstract class NumberProperty<N extends Number & Comparable<? super N>> extends SimpleProperty<N>
    implements WritableNumberProperty<N> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public NumberProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public NumberProperty(String name, PropertyMetadata<N> metadata) {

    super(name, metadata);
  }

  @Override
  protected void readValue(StructuredReader reader) {

    N value = reader.readValue(getValueClass());
    set(value);
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValue(get());
  }

}
