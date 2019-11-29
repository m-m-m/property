/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.biginteger;

import java.math.BigInteger;

import io.github.mmm.property.number.WritableNumberProperty;
import io.github.mmm.value.observable.number.biginteger.WritableBigIntegerValue;

/**
 * {@link WritableNumberProperty} with {@link BigInteger} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableBigIntegerProperty
    extends WritableBigIntegerValue, ReadableBigIntegerProperty, WritableNumberProperty<BigInteger> {

}
