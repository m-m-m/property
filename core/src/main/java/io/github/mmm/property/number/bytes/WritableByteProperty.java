/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.bytes;

import io.github.mmm.property.number.WritableNumberProperty;
import io.github.mmm.value.observable.number.bytes.WritableByteValue;

/**
 * {@link WritableNumberProperty} with {@link Byte} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableByteProperty extends WritableByteValue, ReadableByteProperty, WritableNumberProperty<Byte> {

}
