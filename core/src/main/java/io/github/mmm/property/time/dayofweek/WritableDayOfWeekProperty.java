/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.dayofweek;

import java.time.DayOfWeek;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.time.dayofweek.WritableDayOfWeekValue;

/**
 * {@link WritableProperty} for {@link DayOfWeek} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableDayOfWeekProperty
    extends ReadableDayOfWeekProperty, WritableProperty<DayOfWeek>, WritableDayOfWeekValue {

}
