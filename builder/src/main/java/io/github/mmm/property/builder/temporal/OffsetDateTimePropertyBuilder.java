/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.OffsetDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.temporal.offsetdatetime.OffsetDateTimeProperty;
import io.github.mmm.validation.temporal.offsetdatetime.ValidatorBuilderOffsetDateTime;

/**
 * {@link PropertyBuilder} for {@link OffsetDateTimeProperty}.
 */
public final class OffsetDateTimePropertyBuilder extends
    PropertyBuilder<OffsetDateTime, OffsetDateTimeProperty, ValidatorBuilderOffsetDateTime<OffsetDateTimePropertyBuilder>, OffsetDateTimePropertyBuilder> {

  /**
   * The constructor.
   */
  public OffsetDateTimePropertyBuilder() {

    super();
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
