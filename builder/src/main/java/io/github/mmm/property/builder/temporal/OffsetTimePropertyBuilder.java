/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.temporal;

import java.time.OffsetTime;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.temporal.offsettime.OffsetTimeProperty;
import io.github.mmm.validation.temporal.offsettime.ValidatorBuilderOffsetTime;

/**
 * {@link PropertyBuilder} for {@link OffsetTimeProperty}.
 */
public final class OffsetTimePropertyBuilder extends
    PropertyBuilder<OffsetTime, OffsetTimeProperty, ValidatorBuilderOffsetTime<OffsetTimePropertyBuilder>, OffsetTimePropertyBuilder> {

  /**
   * The constructor.
   */
  public OffsetTimePropertyBuilder() {

    super();
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
