/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.booleans;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.value.observable.booleans.BooleanExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Boolean}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableBooleanProperty extends ReadableProperty<Boolean>, BooleanExpression {

}
