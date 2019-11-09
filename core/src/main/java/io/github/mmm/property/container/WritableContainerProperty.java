/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.container.ChangeAwareContainer;
import io.github.mmm.value.observable.container.WritableContainerValue;

/**
 * {@link WritableProperty} with container {@link #getValue() value}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 * @see ReadableContainerProperty
 */
public interface WritableContainerProperty<V, E>
    extends ReadableContainerProperty<V, E>, WritableProperty<V>, WritableContainerValue<V, E> {

  /**
   * @return {@code true} if {@link #getChangeAwareValue() change aware value} has been initialized, {@code false}
   *         otherwise.
   */
  boolean isChangeAware();

  /**
   * @return the {@link #getValue() value} as {@link ChangeAwareContainer}. Will be initialized on the first call of
   *         this method. Unless {@link #isChangeAware() initialized}, a container property is lightweight just as other
   *         regular properties. Once this method was called, the {@link ChangeAwareContainer} is initialized and
   *         triggers change events for modifications of the container instance itself. Calls to
   *         {@link #setValue(Object)} will implicitly change this {@link ChangeAwareContainer} such that in only
   *         contains what the provided new value contains. The instance of the {@link ChangeAwareContainer} returned by
   *         this method always remains the same.
   */
  ChangeAwareContainer<E, ?, ?> getChangeAwareValue();

}
