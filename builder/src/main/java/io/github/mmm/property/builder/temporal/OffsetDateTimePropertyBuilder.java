/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.OffsetDateTime;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.temporal.offsetdatetime.OffsetDateTimeProperty;
import io.github.mmm.validation.temporal.offsetdatetime.ValidatorBuilderOffsetDateTime;

/**
 * {@link PropertyBuilder} for {@link OffsetDateTimeProperty}.
 *
 * @since 1.0.0
 */
public final class OffsetDateTimePropertyBuilder extends
    ComparablePropertyBuilder<OffsetDateTime, OffsetDateTimeProperty, ValidatorBuilderOffsetDateTime<OffsetDateTimePropertyBuilder>, OffsetDateTimePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public OffsetDateTimePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderOffsetDateTime<OffsetDateTimePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderOffsetDateTime<>(this);
  }

  @Override
  protected OffsetDateTimeProperty build(String name, PropertyMetadata<OffsetDateTime> metadata) {

    return new OffsetDateTimeProperty(name, metadata);
  }

}
