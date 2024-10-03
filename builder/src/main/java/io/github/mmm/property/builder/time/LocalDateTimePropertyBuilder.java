/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.LocalDateTime;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.localdatetime.LocalDateTimeProperty;
import io.github.mmm.validation.time.localdatetime.ValidatorBuilderLocalDateTime;

/**
 * {@link PropertyBuilder} for {@link LocalDateTimeProperty}.
 *
 * @since 1.0.0
 */
public final class LocalDateTimePropertyBuilder extends
    ComparablePropertyBuilder<LocalDateTime, LocalDateTimeProperty, ValidatorBuilderLocalDateTime<LocalDateTimePropertyBuilder>, LocalDateTimePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public LocalDateTimePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
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
