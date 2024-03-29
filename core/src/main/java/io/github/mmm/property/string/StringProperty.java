/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableStringProperty}.
 *
 * @since 1.0.0
 */
public class StringProperty extends SimpleProperty<String> implements WritableStringProperty {

  private String value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringProperty(String name, PropertyMetadata<String> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public StringProperty(String name, String value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringProperty(String name, String value, PropertyMetadata<String> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected String doGet() {

    return this.value;
  }

  @Override
  protected void doSet(String newValue) {

    this.value = newValue;
  }

}
