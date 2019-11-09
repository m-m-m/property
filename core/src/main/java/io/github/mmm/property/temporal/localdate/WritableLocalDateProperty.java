/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localdate;

import java.time.LocalDate;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.temporal.localdate.WritableLocalDateValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link LocalDate}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableLocalDateProperty
    extends ReadableLocalDateProperty, WritableProperty<LocalDate>, WritableLocalDateValue {

}
