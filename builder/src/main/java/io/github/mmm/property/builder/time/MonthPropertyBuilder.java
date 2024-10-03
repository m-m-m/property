/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.Month;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.month.MonthProperty;
import io.github.mmm.validation.time.month.ValidatorBuilderMonth;

/**
 * {@link PropertyBuilder} for {@link MonthProperty}.
 *
 * @since 1.0.0
 */
public final class MonthPropertyBuilder extends
    ComparablePropertyBuilder<Month, MonthProperty, ValidatorBuilderMonth<MonthPropertyBuilder>, MonthPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public MonthPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderMonth<MonthPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderMonth<>(this);
  }

  @Override
  protected MonthProperty build(String name, PropertyMetadata<Month> metadata) {

    return new MonthProperty(name, metadata);
  }

}
