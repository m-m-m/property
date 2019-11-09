/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container;

import java.lang.reflect.Type;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.booleans.ReadableBooleanProperty;
import io.github.mmm.property.number.integers.ReadableIntegerProperty;
import io.github.mmm.value.observable.container.ReadableContainerValue;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link java.util.Collection} or {@link java.util.Map}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableContainerProperty<V, E> extends ReadableProperty<V>, ReadableContainerValue<V, E> {

  /**
   * @return the {@link Class} reflecting the type of the objects contained in the property {@link #getValue() value}.
   * @see #getComponentType()
   */
  Class<E> getComponentClass();

  /**
   * @return the {@link Type} reflecting the type of the objects contained in the property {@link #getValue() value}.
   *         May be the same as {@link #getComponentClass()} but can also be a generic type revealing more details.
   * @see #getComponentClass()
   */
  default Type getComponentType() {

    return getComponentClass();
  }

  /**
   * @return an {@link ReadableIntegerProperty} that represents the {@link java.util.Collection#size()} property of the
   *         {@link #getValue() container}.
   */
  ReadableIntegerProperty sizeProperty();

  /**
   * @return an {@link ReadableBooleanProperty} that represents the {@link java.util.Collection#isEmpty() empty}
   *         property of the {@link #getValue() container}.
   */
  ReadableBooleanProperty emptyProperty();

}
