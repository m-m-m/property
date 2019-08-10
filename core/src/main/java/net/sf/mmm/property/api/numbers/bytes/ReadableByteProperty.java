/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bytes;

import net.sf.mmm.property.api.numbers.ReadableNumberProperty;
import net.sf.mmm.value.observable.numbers.bytes.ByteExpression;

/**
 * {@link ReadableNumberProperty} for {@link Byte} {@link #getValue() value}s.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableByteProperty extends ReadableNumberProperty<Byte>, ByteExpression {

}
