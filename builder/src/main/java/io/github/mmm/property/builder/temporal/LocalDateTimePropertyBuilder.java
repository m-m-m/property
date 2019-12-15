/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.LocalDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.temporal.localdatetime.LocalDateTimeProperty;
import io.github.mmm.validation.temporal.localdatetime.ValidatorBuilderLocalDateTime;

/**
 * {@link PropertyBuilder} for {@link LocalDateTimeProperty}.
 */
public final class LocalDateTimePropertyBuilder extends
    PropertyBuilder<LocalDateTime, LocalDateTimeProperty, ValidatorBuilderLocalDateTime<LocalDateTimePropertyBuilder>, LocalDateTimePropertyBuilder> {

  /**
   * The constructor.
   */
  public LocalDateTimePropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderLocalDateTime<LocalDateTimePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderLocalDateTime<>(this);
  }

  @Override
  protected LocalDateTimeProperty build(String name, PropertyMetadata<LocalDateTime> metadata) {

    return new LocalDateTimeProperty(name, metadata);
  }

}