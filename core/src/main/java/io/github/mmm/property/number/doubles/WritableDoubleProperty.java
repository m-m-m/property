/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.doubles;

import io.github.mmm.property.number.WritableNumberProperty;
import io.github.mmm.value.observable.number.doubles.WritableDoubleValue;

/**
 * {@link WritableNumberProperty} with {@link Double} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableDoubleProperty
    extends WritableDoubleValue, ReadableDoubleProperty, WritableNumberProperty<Double> {

}
