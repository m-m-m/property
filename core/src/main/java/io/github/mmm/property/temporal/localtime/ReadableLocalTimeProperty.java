/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.temporal.localtime;

import java.time.LocalTime;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.temporal.localtime.LocalTimeExpression;

/**
 * {@link ReadableProperty} for {@link LocalTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableLocalTimeProperty extends ReadableProperty<LocalTime>, LocalTimeExpression {

  @Override
  default Class<LocalTime> getValueClass() {

    return LocalTime.class;
  }

}
