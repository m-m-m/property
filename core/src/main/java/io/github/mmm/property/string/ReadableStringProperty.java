/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.string.StringExpression;

/**
 * {@link ReadableProperty} with {@link String} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableStringProperty extends ReadableProperty<String>, StringExpression {

}
