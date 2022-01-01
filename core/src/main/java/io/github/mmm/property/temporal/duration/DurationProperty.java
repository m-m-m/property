/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.duration;

import java.time.Duration;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.temporal.TemporalAmountProperty;

/**
 * Implementation of {@link WritableDurationProperty}.
 *
 * @since 1.0.0
 */
public class DurationProperty extends TemporalAmountProperty<Duration> implements WritableDurationProperty {

  private Duration value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public DurationProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DurationProperty(String name, PropertyMetadata<Duration> metadata) {

    super(name, metadata);
  }

  @Override
  protected Duration doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Duration newValue) {

    this.value = newValue;
  }

}
