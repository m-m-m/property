/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.DayOfWeek;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.dayofweek.DayOfWeekProperty;
import io.github.mmm.validation.time.dayofweek.ValidatorBuilderDayOfWeek;

/**
 * {@link PropertyBuilder} for {@link DayOfWeekProperty}.
 *
 * @since 1.0.0
 */
public final class DayOfWeekPropertyBuilder extends
    ComparablePropertyBuilder<DayOfWeek, DayOfWeekProperty, ValidatorBuilderDayOfWeek<DayOfWeekPropertyBuilder>, DayOfWeekPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public DayOfWeekPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderDayOfWeek<DayOfWeekPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderDayOfWeek<>(this);
  }

  @Override
  protected DayOfWeekProperty build(String name, PropertyMetadata<DayOfWeek> metadata) {

    return new DayOfWeekProperty(name, metadata);
  }

}
