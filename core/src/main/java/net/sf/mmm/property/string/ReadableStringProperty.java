/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.string;

import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.value.observable.string.StringExpression;

/**
 * {@link ReadableProperty} with {@link String} {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableStringProperty extends ReadableProperty<String>, StringExpression {

}
