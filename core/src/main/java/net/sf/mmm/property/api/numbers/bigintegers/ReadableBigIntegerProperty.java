/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigintegers;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.sf.mmm.property.api.numbers.ReadableNumberProperty;
import net.sf.mmm.value.observable.numbers.bigintegers.BigIntegerExpression;

/**
 * {@link ReadableNumberProperty} for {@link BigDecimal} {@link #getValue() value}s.
 *
 * @since 1.0.0
 */
public interface ReadableBigIntegerProperty extends BigIntegerExpression, ReadableNumberProperty<BigInteger> {

}
