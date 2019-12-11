/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.object.WritableSimpleValue;

/**
 * {@link WritableProperty} for a simple datatype (and not a
 * {@link io.github.mmm.property.container.ReadableContainerProperty container property}).
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableSimpleProperty<V>
    extends ReadableSimpleProperty<V>, WritableSimpleValue<V>, WritableProperty<V> {

}
