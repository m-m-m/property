/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.locale;

import java.util.Locale;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.object.ReadableSimpleProperty;
import io.github.mmm.value.observable.locale.ReadableLocaleValue;

/**
 * {@link ReadableProperty} with {@link Locale} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableLocaleProperty extends ReadableSimpleProperty<Locale>, ReadableLocaleValue {

}
