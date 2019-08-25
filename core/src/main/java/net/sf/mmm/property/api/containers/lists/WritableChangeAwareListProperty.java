/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.lists;

import java.util.List;

import net.sf.mmm.property.api.containers.collections.WritableChangeAwareCollectionProperty;
import net.sf.mmm.value.observable.containers.lists.WritableChangeAwareListValue;

/**
 * {@link WritableListProperty} with {@link net.sf.mmm.value.observable.containers.lists.ChangeAwareList}
 * {@link #getValue() value}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public interface WritableChangeAwareListProperty<E> extends ReadableChangeAwareListProperty<E>,
    WritableChangeAwareCollectionProperty<List<E>, E>, WritableChangeAwareListValue<E> {

}
