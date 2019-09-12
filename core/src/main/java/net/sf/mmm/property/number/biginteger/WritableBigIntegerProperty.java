/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.biginteger;

import java.math.BigInteger;

import net.sf.mmm.property.number.WritableNumberProperty;
import net.sf.mmm.value.observable.number.biginteger.WritableBigIntegerValue;

/**
 * {@link WritableNumberProperty} with {@link BigInteger} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableBigIntegerProperty
    extends WritableBigIntegerValue, ReadableBigIntegerProperty, WritableNumberProperty<BigInteger> {

}