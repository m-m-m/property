/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.localdate;

import java.time.LocalDate;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;

/**
 * This is the implementation of {@link WritableLocalDateProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LocalDateProperty extends Property<LocalDate> implements WritableLocalDateProperty {

  private LocalDate value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public LocalDateProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public LocalDateProperty(String name, PropertyMetadata<LocalDate> metadata) {

    super(name, metadata);
  }

  @Override
  protected LocalDate doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(LocalDate newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderLocalDate<PropertyBuilder<LocalDateProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderLocalDate<>(x));
  // }

}
