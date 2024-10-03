/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsettime;

import java.time.OffsetTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.time.TemporalProperty;

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

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public OffsetTimeProperty(String name, PropertyMetadata<OffsetTime> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public OffsetTimeProperty(String name, OffsetTime value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public OffsetTimeProperty(String name, OffsetTime value, PropertyMetadata<OffsetTime> metadata) {

    super(name, metadata);
    this.value = value;
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
