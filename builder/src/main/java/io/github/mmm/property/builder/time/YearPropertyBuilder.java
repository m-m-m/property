/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.Year;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.year.YearProperty;
import io.github.mmm.validation.time.year.ValidatorBuilderYear;

/**
 * {@link PropertyBuilder} for {@link YearProperty}.
 *
 * @since 1.0.0
 */
public final class YearPropertyBuilder extends
    ComparablePropertyBuilder<Year, YearProperty, ValidatorBuilderYear<YearPropertyBuilder>, YearPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public YearPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderYear<YearPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderYear<>(this);
  }

  @Override
  protected YearProperty build(String name, PropertyMetadata<Year> metadata) {

    return new YearProperty(name, metadata);
  }

}
