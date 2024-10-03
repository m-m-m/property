/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.year;

import java.time.Year;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.time.year.WritableYearValue;

/**
 * {@link WritableProperty} for {@link Year} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableYearProperty extends ReadableYearProperty, WritableProperty<Year>, WritableYearValue {

}
