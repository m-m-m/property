/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.instant;

import java.time.Instant;

import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.value.observable.temporal.instant.WritableInstantValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link Instant}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableInstantProperty
    extends ReadableInstantProperty, WritableProperty<Instant>, WritableInstantValue {

}