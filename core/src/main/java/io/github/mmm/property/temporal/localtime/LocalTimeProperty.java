/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localtime;

import java.time.LocalTime;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.temporal.TemporalProperty;

/**
 * {@link Property} for {@link LocalTime}.
 *
 * @since 1.0.0
 */
public class LocalTimeProperty extends TemporalProperty<LocalTime> implements WritableLocalTimeProperty {

  private LocalTime value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public LocalTimeProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public LocalTimeProperty(String name, PropertyMetadata<LocalTime> metadata) {

    super(name, metadata);
  }

  @Override
  protected LocalTime doGet() {

    return this.value;
  }

  @Override
  protected void doSet(LocalTime newValue) {

    this.value = newValue;
  }

}
