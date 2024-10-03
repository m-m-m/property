/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.LocalTime;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.localtime.LocalTimeProperty;
import io.github.mmm.validation.time.localtime.ValidatorBuilderLocalTime;

/**
 * {@link PropertyBuilder} for {@link LocalTimeProperty}.
 *
 * @since 1.0.0
 */
public final class LocalTimePropertyBuilder extends
    ComparablePropertyBuilder<LocalTime, LocalTimeProperty, ValidatorBuilderLocalTime<LocalTimePropertyBuilder>, LocalTimePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public LocalTimePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderLocalTime<LocalTimePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderLocalTime<>(this);
  }

  @Override
  protected LocalTimeProperty build(String name, PropertyMetadata<LocalTime> metadata) {

    return new LocalTimeProperty(name, metadata);
  }

}
