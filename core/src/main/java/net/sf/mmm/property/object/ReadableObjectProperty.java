/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.object;

import java.lang.reflect.Type;

import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.value.observable.Expression;
import net.sf.mmm.value.observable.object.ObservableObjectValue;

/**
 * {@link ReadableProperty} with generic {@link Object} {@link #getValue() value}.
 *
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableObjectProperty<V> extends ReadableProperty<V>, ObservableObjectValue<V>, Expression<V> {

  /**
   * @return the {@link Type} reflecting the {@link #getValue() value}. May be the same as {@link #getValueClass()} or
   *         may contain additional generic type information.
   * @see #getValueClass()
   */
  default Type getValueType() {

    return getValueClass();
  }

}
