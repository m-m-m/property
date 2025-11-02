/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.month;

import java.time.Month;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.comparable.WritableComparableProperty;
import io.github.mmm.value.observable.time.month.WritableMonthValue;

/**
 * {@link WritableProperty} for {@link Month} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableMonthProperty
    extends ReadableMonthProperty, WritableComparableProperty<Month>, WritableMonthValue {

}
