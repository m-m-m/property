/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.number;

import java.math.BigInteger;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.builder.lang.ComparablePropertyBuilder;
import io.github.mmm.property.number.biginteger.BigIntegerProperty;
import io.github.mmm.validation.number.ValidatorBuilderBigInteger;

/**
 * {@link PropertyBuilder} for {@link BigIntegerProperty}.
 *
 * @since 1.0.0
 */
public final class BigIntegerPropertyBuilder extends
    ComparablePropertyBuilder<BigInteger, BigIntegerProperty, ValidatorBuilderBigInteger<BigIntegerPropertyBuilder>, BigIntegerPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public BigIntegerPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderBigInteger<BigIntegerPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderBigInteger<>(this);
  }

  @Override
  protected BigIntegerProperty build(String name, PropertyMetadata<BigInteger> metadata) {

    return new BigIntegerProperty(name, metadata);
  }

}
