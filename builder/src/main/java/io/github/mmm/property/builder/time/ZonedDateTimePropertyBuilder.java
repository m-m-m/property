/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.ZonedDateTime;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.zoneddatetime.ZonedDateTimeProperty;
import io.github.mmm.validation.time.zoneddatetime.ValidatorBuilderZonedDateTime;

/**
 * {@link PropertyBuilder} for {@link ZonedDateTimeProperty}.
 *
 * @since 1.0.0
 */
public final class ZonedDateTimePropertyBuilder extends
    ComparablePropertyBuilder<ZonedDateTime, ZonedDateTimeProperty, ValidatorBuilderZonedDateTime<ZonedDateTimePropertyBuilder>, ZonedDateTimePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public ZonedDateTimePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
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
