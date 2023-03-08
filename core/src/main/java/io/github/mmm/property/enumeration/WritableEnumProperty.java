/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.enumeration;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;
import io.github.mmm.value.observable.enumeration.WritableEnumValue;

/**
 * {@link WritableProperty} with {@link Enum} {@link #get() value}.
 *
 * @param <E> type of {@link Enum} {@link #getValue() value}.
 * @since 1.0.0
 */
public interface WritableEnumProperty<E extends Enum<E>>
    extends ReadableEnumProperty<E>, WritableSimpleProperty<E>, WritableEnumValue<E> {

}
