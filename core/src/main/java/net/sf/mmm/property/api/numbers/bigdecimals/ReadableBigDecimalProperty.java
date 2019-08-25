/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigdecimals;

import java.math.BigDecimal;

import net.sf.mmm.property.api.numbers.ReadableNumberProperty;
import net.sf.mmm.value.observable.numbers.bigdecimals.BigDecimalExpression;

/**
 * {@link ReadableNumberProperty} with {@link BigDecimal} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableBigDecimalProperty extends BigDecimalExpression, ReadableNumberProperty<BigDecimal> {

}
