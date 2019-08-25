/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers;

import net.sf.mmm.property.api.booleans.ReadableBooleanProperty;
import net.sf.mmm.property.api.numbers.integers.ReadableIntegerProperty;
import net.sf.mmm.value.observable.containers.ReadableChangeAwareContainerValue;

/**
 * {@link ReadableContainerProperty} with {@link net.sf.mmm.value.observable.containers.ChangeAwareContainer}
 * {@link #getValue() value}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableChangeAwareContainerProperty<V, E>
    extends ReadableContainerProperty<V, E>, ReadableChangeAwareContainerValue<V, E> {

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
