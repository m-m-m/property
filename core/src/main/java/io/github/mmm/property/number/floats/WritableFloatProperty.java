/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.floats;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.number.WritableNumberProperty;
import io.github.mmm.value.observable.number.floats.WritableFloatValue;

/**
 * {@link WritableProperty} with {@link Float} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableFloatProperty
    extends WritableFloatValue, ReadableFloatProperty, WritableNumberProperty<Float> {

}
