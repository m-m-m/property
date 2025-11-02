/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.comparable.WritableComparableProperty;
import io.github.mmm.value.observable.time.offsetdatetime.WritableOffsetDateTimeValue;

/**
 * {@link WritableProperty} for {@link OffsetDateTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableOffsetDateTimeProperty
    extends ReadableOffsetDateTimeProperty, WritableComparableProperty<OffsetDateTime>, WritableOffsetDateTimeValue {

}
