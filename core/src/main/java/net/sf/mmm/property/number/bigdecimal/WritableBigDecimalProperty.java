/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import net.sf.mmm.property.number.WritableNumberProperty;
import net.sf.mmm.value.observable.number.bigdecimal.WritableBigDecimalValue;

/**
 * {@link WritableNumberProperty} with {@link BigDecimal} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableBigDecimalProperty
    extends WritableBigDecimalValue, ReadableBigDecimalProperty, WritableNumberProperty<BigDecimal> {

}
