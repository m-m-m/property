/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.temporal.zoneddatetime.WritableZonedDateTimeValue;

/**
 * {@link WritableProperty} for {@link ZonedDateTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableZonedDateTimeProperty
    extends ReadableZonedDateTimeProperty, WritableProperty<ZonedDateTime>, WritableZonedDateTimeValue {

}
