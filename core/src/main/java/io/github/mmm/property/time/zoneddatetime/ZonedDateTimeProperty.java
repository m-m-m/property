/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.time.TemporalProperty;

/**
 * This is the implementation of {@link WritableZonedDateTimeProperty}.
 *
 * @since 1.0.0
 */
public class ZonedDateTimeProperty extends TemporalProperty<ZonedDateTime> implements WritableZonedDateTimeProperty {

  private ZonedDateTime value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public ZonedDateTimeProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ZonedDateTimeProperty(String name, PropertyMetadata<ZonedDateTime> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public ZonedDateTimeProperty(String name, ZonedDateTime value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ZonedDateTimeProperty(String name, ZonedDateTime value, PropertyMetadata<ZonedDateTime> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected ZonedDateTime doGet() {

    return this.value;
  }

  @Override
  protected void doSet(ZonedDateTime newValue) {

    this.value = newValue;
  }

}
