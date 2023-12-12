/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.util.Map;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.container.ReadableContainerProperty;
import io.github.mmm.value.observable.container.map.ObservableMapValue;

/**
 * {@link ReadableContainerProperty} with {@link Map} {@link #getValue() value}.
 *
 * @param <K> type of the {@link Map#containsKey(Object) keys}.
 * @param <V> type of the {@link Map#containsValue(Object) values}.
 *
 * @since 1.0.0
 */
public interface ReadableMapProperty<K, V> extends ObservableMapValue<K, V>, ReadableContainerProperty<Map<K, V>, V> {

  /**
   * @return the {@link ReadableProperty} representing the type of the {@link Map#keySet() keys} of the {@link Map}
   *         {@link #getValue() value}.
   */
  ReadableProperty<K> getKeyProperty();

}
