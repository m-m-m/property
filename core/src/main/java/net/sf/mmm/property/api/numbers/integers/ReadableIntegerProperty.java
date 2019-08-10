/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.integers;

import net.sf.mmm.property.api.numbers.ReadableNumberProperty;
import net.sf.mmm.value.observable.numbers.integers.IntegerExpression;

/**
 * This is the interface for a {@link ReadableNumberProperty} of the {@link #getValue() value}-{@link #getValueClass()
 * type} {@link Integer}.
 *
 * @since 1.0.0
 */
public interface ReadableIntegerProperty extends ReadableNumberProperty<Integer>, IntegerExpression {

}
