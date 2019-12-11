/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.offsettime;

import java.time.OffsetTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.temporal.TemporalProperty;

/**
 * This is the implementation of {@link WritableOffsetTimeProperty}.
 *
 * @since 1.0.0
 */
public class OffsetTimeProperty extends TemporalProperty<OffsetTime> implements WritableOffsetTimeProperty {

  private OffsetTime value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public OffsetTimeProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public OffsetTimeProperty(String name, PropertyMetadata<OffsetTime> metadata) {

    super(name, metadata);
  }

  @Override
  protected OffsetTime doGet() {

    return this.value;
  }

  @Override
  protected void doSet(OffsetTime newValue) {

    this.value = newValue;
  }

}
