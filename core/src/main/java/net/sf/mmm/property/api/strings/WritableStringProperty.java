/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.strings;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.strings.WritableStringValue;

/**
 * {@link WritableProperty} with {@link String} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableStringProperty extends ReadableStringProperty, WritableProperty<String>, WritableStringValue {

}
