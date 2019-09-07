/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.instant;

import java.time.Instant;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;

/**
 * This is the implementation of {@link WritableInstantProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class InstantProperty extends Property<Instant> implements WritableInstantProperty {

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
  protected Instant doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Instant newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderInstant<PropertyBuilder<InstantProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderInstant<>(x));
  // }

}
