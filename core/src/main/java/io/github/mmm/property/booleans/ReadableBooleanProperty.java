/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.booleans;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.object.ReadableSimpleProperty;
import io.github.mmm.value.observable.booleans.BooleanExpression;

/**
 * {@link ReadableProperty} with {@link Boolean} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableBooleanProperty extends BooleanExpression, ReadableSimpleProperty<Boolean> {

}
