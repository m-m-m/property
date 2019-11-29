/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import io.github.mmm.property.number.ReadableNumberProperty;
import io.github.mmm.value.observable.number.bigdecimal.BigDecimalExpression;

/**
 * {@link ReadableNumberProperty} with {@link BigDecimal} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableBigDecimalProperty extends BigDecimalExpression, ReadableNumberProperty<BigDecimal> {

}
