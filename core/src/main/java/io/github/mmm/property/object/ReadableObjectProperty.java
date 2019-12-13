/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.Expression;
import io.github.mmm.value.observable.object.ObservableObjectValue;

/**
 * {@link ReadableProperty} with generic {@link Object} {@link #get() value}.
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableObjectProperty<V> extends ReadableProperty<V>, ObservableObjectValue<V>, Expression<V> {

}
