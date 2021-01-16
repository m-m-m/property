/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import java.lang.reflect.Type;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.Expression;
import io.github.mmm.value.observable.object.ObservableSimpleValue;

/**
 * {@link ReadableProperty} for a simple datatype (and not a
 * {@link io.github.mmm.property.container.ReadableContainerProperty container property}).
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public interface ReadableSimpleProperty<V> extends ReadableProperty<V>, ObservableSimpleValue<V>, Expression<V> {

  /**
   * @return the {@link Type} reflecting the {@link #get() value}. May be the same as {@link #getValueClass()} or may
   *         contain additional generic type information.
   * @see #getValueClass()
   */
  default Type getValueType() {

    return getValueClass();
  }

}
