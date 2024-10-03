/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.localdate;

import java.time.LocalDate;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.time.localdate.LocalDateExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #get() value}-{@link #getValueClass() type}
 * {@link LocalDate}.
 *
 * @since 1.0.0
 */
public interface ReadableLocalDateProperty extends ReadableProperty<LocalDate>, LocalDateExpression {

}
