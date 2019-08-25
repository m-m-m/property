/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.objects;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.objects.WritableObjectValue;

/**
 * This is the interface for a {@link WritableProperty} with a non-primitive the {@link #getValue() value}-
 * {@link #getValueClass() type}.
 *
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableObjectProperty<V> extends ReadableObjectProperty<V>, WritableObjectValue<V> {

}
