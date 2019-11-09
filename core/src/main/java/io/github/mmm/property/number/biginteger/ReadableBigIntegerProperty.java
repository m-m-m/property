/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.biginteger;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.github.mmm.property.number.ReadableNumberProperty;
import io.github.mmm.value.observable.number.biginteger.BigIntegerExpression;

/**
 * {@link ReadableNumberProperty} for {@link BigDecimal} {@link #getValue() value}s.
 *
 * @since 1.0.0
 */
public interface ReadableBigIntegerProperty extends BigIntegerExpression, ReadableNumberProperty<BigInteger> {

}
