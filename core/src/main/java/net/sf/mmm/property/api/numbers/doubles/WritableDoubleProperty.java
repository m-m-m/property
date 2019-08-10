/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.doubles;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.numbers.WritableNumberProperty;
import net.sf.mmm.value.observable.numbers.doubles.WritableDoubleValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Double}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableDoubleProperty
    extends ReadableDoubleProperty, WritableNumberProperty<Double>, WritableDoubleValue {

}
