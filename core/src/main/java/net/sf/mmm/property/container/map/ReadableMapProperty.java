/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.container.map;

import java.util.Map;

import net.sf.mmm.property.container.ReadableContainerProperty;
import net.sf.mmm.value.observable.container.map.ObservableMapValue;

/**
 * {@link ReadableContainerProperty} with {@link Map} {@link #getValue() value}.
 *
 * @param <K> type of the {@link Map#containsKey(Object) keys}.
 * @param <V> type of the {@link Map#containsValue(Object) values}.
 *
 * @since 1.0.0
 */
public interface ReadableMapProperty<K, V> extends ObservableMapValue<K, V>, ReadableContainerProperty<Map<K, V>, V> {

}
