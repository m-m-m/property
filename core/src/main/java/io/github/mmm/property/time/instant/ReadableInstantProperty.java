/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.instant;

import java.time.Instant;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.time.instant.InstantExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #get() value}-{@link #getValueClass() type}
 * {@link Instant}.
 *
 * @since 1.0.0
 */
public interface ReadableInstantProperty extends ReadableProperty<Instant>, InstantExpression {

}
