/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.longs.LongProperty;
import io.github.mmm.validation.number.ValidatorBuilderLong;

/**
 * {@link PropertyBuilder} for {@link LongProperty}.
 *
 * @since 1.0.0
 */
public final class LongPropertyBuilder extends
    ComparablePropertyBuilder<Long, LongProperty, ValidatorBuilderLong<LongPropertyBuilder>, LongPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public LongPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderLong<LongPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderLong<>(this);
  }

  @Override
  protected LongProperty build(String name, PropertyMetadata<Long> metadata) {

    return new LongProperty(name, metadata);
  }

}
