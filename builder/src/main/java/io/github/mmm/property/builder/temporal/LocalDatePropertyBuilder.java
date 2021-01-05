/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.LocalDate;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.temporal.localdate.LocalDateProperty;
import io.github.mmm.validation.temporal.localdate.ValidatorBuilderLocalDate;

/**
 * {@link PropertyBuilder} for {@link LocalDateProperty}.
 *
 * @since 1.0.0
 */
public final class LocalDatePropertyBuilder extends
    ComparablePropertyBuilder<LocalDate, LocalDateProperty, ValidatorBuilderLocalDate<LocalDatePropertyBuilder>, LocalDatePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public LocalDatePropertyBuilder(PropertyBuilders parent) {

    super(parent);
  }

  @Override
  protected ValidatorBuilderLocalDate<LocalDatePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderLocalDate<>(this);
  }

  @Override
  protected LocalDateProperty build(String name, PropertyMetadata<LocalDate> metadata) {

    return new LocalDateProperty(name, metadata);
  }

}
