/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.duration;

import java.time.Duration;
import java.time.Instant;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.temporal.duration.WritableDurationValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #get() value}-{@link #getValueClass() type}
 * {@link Instant}.
 *
 * @since 1.0.0
 */
public interface WritableDurationProperty
    extends ReadableDurationProperty, WritableProperty<Duration>, WritableDurationValue {

}
