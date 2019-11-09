/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.temporal.localdatetime.WritableLocalDateTimeValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link LocalDateTime}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableLocalDateTimeProperty
    extends ReadableLocalDateTimeProperty, WritableProperty<LocalDateTime>, WritableLocalDateTimeValue {

}
