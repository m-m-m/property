/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.booleans;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.booleans.WritableBooleanValue;

/**
 * {@link WritableProperty} with {@link Boolean} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableBooleanProperty
    extends ReadableBooleanProperty, WritableProperty<Boolean>, WritableBooleanValue {

}
