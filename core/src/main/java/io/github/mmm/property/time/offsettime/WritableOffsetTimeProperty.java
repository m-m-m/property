/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsettime;

import java.time.OffsetTime;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.value.observable.time.offsettime.WritableOffsetTimeValue;

/**
 * {@link WritableProperty} for {@link OffsetTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableOffsetTimeProperty
    extends ReadableOffsetTimeProperty, WritableProperty<OffsetTime>, WritableOffsetTimeValue {

}
