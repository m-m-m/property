/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.ZonedDateTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.temporal.zoneddatetime.ZonedDateTimeProperty;
import io.github.mmm.validation.temporal.zoneddatetime.ValidatorBuilderZonedDateTime;

/**
 * {@link PropertyBuilder} for {@link ZonedDateTimeProperty}.
 *
 * @since 1.0.0
 */
public final class ZonedDateTimePropertyBuilder extends
    ComparablePropertyBuilder<ZonedDateTime, ZonedDateTimeProperty, ValidatorBuilderZonedDateTime<ZonedDateTimePropertyBuilder>, ZonedDateTimePropertyBuilder> {

  /**
   * The constructor.
   */
  public ZonedDateTimePropertyBuilder() {

    super();
  }

  @Override
  protected ValidatorBuilderZonedDateTime<ZonedDateTimePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderZonedDateTime<>(this);
  }

  @Override
  protected ZonedDateTimeProperty build(String name, PropertyMetadata<ZonedDateTime> metadata) {

    return new ZonedDateTimeProperty(name, metadata);
  }

}
