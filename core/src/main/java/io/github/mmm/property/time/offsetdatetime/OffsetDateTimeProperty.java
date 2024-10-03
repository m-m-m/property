/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.time.TemporalProperty;

/**
 * This is the implementation of {@link WritableOffsetDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class OffsetDateTimeProperty extends TemporalProperty<OffsetDateTime> implements WritableOffsetDateTimeProperty {

  private OffsetDateTime value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public OffsetDateTimeProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public OffsetDateTimeProperty(String name, PropertyMetadata<OffsetDateTime> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public OffsetDateTimeProperty(String name, OffsetDateTime value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public OffsetDateTimeProperty(String name, OffsetDateTime value, PropertyMetadata<OffsetDateTime> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected OffsetDateTime doGet() {

    return this.value;
  }

  @Override
  protected void doSet(OffsetDateTime newValue) {

    this.value = newValue;
  }

}
