/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigintegers;

import java.math.BigInteger;

import net.sf.mmm.property.api.numbers.WritableNumberProperty;
import net.sf.mmm.value.observable.numbers.bigintegers.WritableBigIntegerValue;

/**
 * {@link WritableNumberProperty} for {@link BigInteger} {@link #getValue() value}s.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableBigIntegerProperty
    extends ReadableBigIntegerProperty, WritableNumberProperty<BigInteger>, WritableBigIntegerValue {

}
