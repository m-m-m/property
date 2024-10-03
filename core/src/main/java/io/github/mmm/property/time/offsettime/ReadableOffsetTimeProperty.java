/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsettime;

import java.time.OffsetTime;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.time.offsettime.OffsetTimeExpression;

/**
 * {@link ReadableProperty} for {@link OffsetTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableOffsetTimeProperty extends ReadableProperty<OffsetTime>, OffsetTimeExpression {

  @Override
  default Class<OffsetTime> getValueClass() {

    return OffsetTime.class;
  }

}
