/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.longs;

import net.sf.mmm.property.number.WritableNumberProperty;
import net.sf.mmm.value.observable.number.longs.WritableLongValue;

/**
 * {@link WritableNumberProperty} with {@link Long} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableLongProperty extends ReadableLongProperty, WritableNumberProperty<Long>, WritableLongValue {

}