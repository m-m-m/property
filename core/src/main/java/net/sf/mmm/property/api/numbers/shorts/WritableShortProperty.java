/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.shorts;

import net.sf.mmm.property.api.numbers.WritableNumberProperty;
import net.sf.mmm.value.observable.numbers.shorts.WritableShortValue;

/**
 * {@link WritableNumberProperty} with {@link Short} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableShortProperty
    extends ReadableShortProperty, WritableNumberProperty<Short>, WritableShortValue {

}
