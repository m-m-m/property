/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.booleans;

import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.value.observable.booleans.BooleanExpression;

/**
 * {@link ReadableProperty} with {@link Boolean} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableBooleanProperty extends BooleanExpression, ReadableProperty<Boolean> {

}
