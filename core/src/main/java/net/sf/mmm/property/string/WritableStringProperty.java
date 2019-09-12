/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.string;

import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.value.observable.string.WritableStringValue;

/**
 * {@link WritableProperty} with {@link String} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableStringProperty extends ReadableStringProperty, WritableProperty<String>, WritableStringValue {

}