/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.string.WritableStringValue;

/**
 * {@link WritableProperty} with {@link String} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableStringProperty extends ReadableStringProperty, WritableProperty<String>, WritableStringValue {

}
