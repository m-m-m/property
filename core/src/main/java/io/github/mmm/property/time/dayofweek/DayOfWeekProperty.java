/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.dayofweek;

import java.time.DayOfWeek;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * This is the implementation of {@link WritableDayOfWeekProperty}.
 *
 * @since 1.0.0
 */
public class DayOfWeekProperty extends SimpleProperty<DayOfWeek> implements WritableDayOfWeekProperty {

  private DayOfWeek value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public DayOfWeekProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DayOfWeekProperty(String name, PropertyMetadata<DayOfWeek> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public DayOfWeekProperty(String name, DayOfWeek value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DayOfWeekProperty(String name, DayOfWeek value, PropertyMetadata<DayOfWeek> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected DayOfWeek doGet() {

    return this.value;
  }

  @Override
  protected void doSet(DayOfWeek newValue) {

    this.value = newValue;
  }

}
