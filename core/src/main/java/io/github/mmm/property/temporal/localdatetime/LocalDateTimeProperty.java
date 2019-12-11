/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.temporal.TemporalProperty;

/**
 * This is the implementation of {@link WritableLocalDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class LocalDateTimeProperty extends TemporalProperty<LocalDateTime> implements WritableLocalDateTimeProperty {

  private LocalDateTime value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public LocalDateTimeProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public LocalDateTimeProperty(String name, PropertyMetadata<LocalDateTime> metadata) {

    super(name, metadata);
  }

  @Override
  protected LocalDateTime doGet() {

    return this.value;
  }

  @Override
  protected void doSet(LocalDateTime newValue) {

    this.value = newValue;
  }

}
