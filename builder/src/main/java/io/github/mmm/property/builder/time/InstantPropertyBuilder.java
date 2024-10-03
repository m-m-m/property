/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.time;

import java.time.Instant;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.time.instant.InstantProperty;
import io.github.mmm.validation.time.instant.ValidatorBuilderInstant;

/**
 * {@link PropertyBuilder} for {@link InstantProperty}.
 *
 * @since 1.0.0
 */
public final class InstantPropertyBuilder extends
    ComparablePropertyBuilder<Instant, InstantProperty, ValidatorBuilderInstant<InstantPropertyBuilder>, InstantPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public InstantPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderInstant<InstantPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderInstant<>(this);
  }

  @Override
  protected InstantProperty build(String name, PropertyMetadata<Instant> metadata) {

    return new InstantProperty(name, metadata);
  }

}
