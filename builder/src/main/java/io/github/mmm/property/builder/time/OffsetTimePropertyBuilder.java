/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.OffsetTime;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.offsettime.OffsetTimeProperty;
import io.github.mmm.validation.time.offsettime.ValidatorBuilderOffsetTime;

/**
 * {@link PropertyBuilder} for {@link OffsetTimeProperty}.
 *
 * @since 1.0.0
 */
public final class OffsetTimePropertyBuilder extends
    ComparablePropertyBuilder<OffsetTime, OffsetTimeProperty, ValidatorBuilderOffsetTime<OffsetTimePropertyBuilder>, OffsetTimePropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public OffsetTimePropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderOffsetTime<OffsetTimePropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderOffsetTime<>(this);
  }

  @Override
  protected OffsetTimeProperty build(String name, PropertyMetadata<OffsetTime> metadata) {

    return new OffsetTimeProperty(name, metadata);
  }

}
