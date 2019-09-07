/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.floats;

import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.number.WritableNumberProperty;
import net.sf.mmm.value.observable.number.floats.WritableFloatValue;

/**
 * {@link WritableProperty} with {@link Float} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableFloatProperty
    extends WritableFloatValue, ReadableFloatProperty, WritableNumberProperty<Float> {

}
