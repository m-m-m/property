/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;

/**
 * {@link Property} implementation of {@link WritableSimpleProperty}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public abstract class SimpleProperty<V> extends Property<V> implements WritableSimpleProperty<V> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public SimpleProperty(String name, PropertyMetadata<V> metadata) {

    super(name, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public SimpleProperty(String name) {

    super(name);
  }

  @Override
  protected void readValue(StructuredReader reader) {

    String value = reader.readValueAsString();
    setAsString(value);
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValueAsString(getAsString());
  }

}
