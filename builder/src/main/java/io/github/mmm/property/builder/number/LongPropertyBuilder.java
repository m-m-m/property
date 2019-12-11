/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.number.longs.LongProperty;
import io.github.mmm.validation.number.ValidatorBuilderLong;

/**
 * {@link PropertyBuilder} for {@link LongProperty}.
 */
public final class LongPropertyBuilder
    extends PropertyBuilder<Long, LongProperty, ValidatorBuilderLong<LongPropertyBuilder>, LongPropertyBuilder> {

  /**
   * The constructor.
   */
  public LongPropertyBuilder() {

    super();
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
