/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.instant;

import java.time.Instant;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.temporal.TemporalProperty;

/**
 * Implementation of {@link WritableInstantProperty}.
 *
 * @since 1.0.0
 */
public class InstantProperty extends TemporalProperty<Instant> implements WritableInstantProperty {

  private Instant value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public InstantProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public InstantProperty(String name, PropertyMetadata<Instant> metadata) {

    super(name, metadata);
  }

  @Override
  protected Instant doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Instant newValue) {

    this.value = newValue;
  }

}
