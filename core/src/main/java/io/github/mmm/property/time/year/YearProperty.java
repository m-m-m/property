/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.year;

import java.time.Year;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * This is the implementation of {@link WritableYearProperty}.
 *
 * @since 1.0.0
 */
public class YearProperty extends SimpleProperty<Year> implements WritableYearProperty {

  private Year value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public YearProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public YearProperty(String name, PropertyMetadata<Year> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public YearProperty(String name, Year value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public YearProperty(String name, Year value, PropertyMetadata<Year> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Year doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Year newValue) {

    this.value = newValue;
  }

}
