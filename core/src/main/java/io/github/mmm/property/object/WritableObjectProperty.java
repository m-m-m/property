/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.object.WritableObjectValue;

/**
 * This is the interface for a {@link WritableProperty} with a non-primitive the {@link #get() value}-
 * {@link #getValueClass() type}.
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableObjectProperty<V>
    extends ReadableObjectProperty<V>, WritableObjectValue<V>, WritableSimpleProperty<V> {

}
