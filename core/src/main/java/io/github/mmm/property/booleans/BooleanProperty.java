/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.booleans;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableBooleanProperty}.
 *
 * @since 1.0.0
 */
public class BooleanProperty extends SimpleProperty<Boolean> implements WritableBooleanProperty {

  private Boolean value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public BooleanProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BooleanProperty(String name, PropertyMetadata<Boolean> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public BooleanProperty(String name, Boolean value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public BooleanProperty(String name, Boolean value, PropertyMetadata<Boolean> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Boolean doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Boolean newValue) {

    this.value = newValue;
  }

  @Override
  public void read(StructuredReader reader) {

    set(reader.readValueAsBoolean());
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValueAsBoolean(get());
  }

}
