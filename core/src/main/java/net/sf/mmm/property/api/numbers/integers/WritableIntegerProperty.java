/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.integers;

import net.sf.mmm.property.api.numbers.WritableNumberProperty;
import net.sf.mmm.value.observable.numbers.integers.WritableIntegerValue;

/**
 * This is the interface for a {@link WritableNumberProperty} of the {@link #getValue() value}-{@link #getValueClass()
 * type} {@link Integer}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableIntegerProperty
    extends ReadableIntegerProperty, WritableNumberProperty<Integer>, WritableIntegerValue {

}
