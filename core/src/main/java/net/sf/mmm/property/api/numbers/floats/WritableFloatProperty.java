/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.floats;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.numbers.WritableNumberProperty;
import net.sf.mmm.value.observable.numbers.floats.WritableFloatValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Float}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableFloatProperty
    extends ReadableFloatProperty, WritableNumberProperty<Float>, WritableFloatValue {

}
