/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.objects;

import java.lang.reflect.Type;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.value.observable.Expression;
import net.sf.mmm.value.observable.objects.ObservableObjectValue;

/**
 * This is the interface for a {@link ReadableProperty} with a non-primitive the {@link #getValue() value}-
 * {@link #getValueClass() type}.
 *
 * @param <V> is the generic type of the {@link #getValue() value}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableObjectProperty<V> extends ReadableProperty<V>, ObservableObjectValue<V>, Expression<V> {

  Type getValueType();

}
