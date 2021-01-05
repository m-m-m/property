/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.LocalTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.temporal.localtime.LocalTimeProperty;
import io.github.mmm.validation.temporal.localtime.ValidatorBuilderLocalTime;

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
   * @param parent the {@link PropertyBuilders}.
   */
  public LocalTimePropertyBuilder(PropertyBuilders parent) {

    super(parent);
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
