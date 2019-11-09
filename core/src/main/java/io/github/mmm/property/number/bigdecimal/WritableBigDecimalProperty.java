/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import io.github.mmm.property.number.WritableNumberProperty;
import io.github.mmm.value.observable.number.bigdecimal.WritableBigDecimalValue;

/**
 * {@link WritableNumberProperty} with {@link BigDecimal} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableBigDecimalProperty
    extends WritableBigDecimalValue, ReadableBigDecimalProperty, WritableNumberProperty<BigDecimal> {

}
