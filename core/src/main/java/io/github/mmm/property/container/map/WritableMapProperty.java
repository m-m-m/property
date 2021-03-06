/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.util.Map;

import io.github.mmm.property.container.WritableContainerProperty;
import io.github.mmm.value.observable.container.map.ChangeAwareMap;
import io.github.mmm.value.observable.container.map.WritableMapValue;

/**
 * {@link WritableContainerProperty} with {@link Map} {@link #getValue() value}.
 *
 * @param <K> type of the {@link Map#containsKey(Object) keys}.
 * @param <V> type of the {@link Map#containsValue(Object) values}.
 *
 * @since 1.0.0
 */
public interface WritableMapProperty<K, V>
    extends ReadableMapProperty<K, V>, WritableContainerProperty<Map<K, V>, V>, WritableMapValue<K, V> {

  @Override
  ChangeAwareMap<K, V> getChangeAwareValue();

}
