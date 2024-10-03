/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.time.zoneddatetime.ZonedDateTimeExpression;

/**
 * {@link ReadableProperty} for {@link ZonedDateTime} {@link #get() value}..
 *
 * @since 1.0.0
 */
public interface ReadableZonedDateTimeProperty extends ReadableProperty<ZonedDateTime>, ZonedDateTimeExpression {

  @Override
  default Class<ZonedDateTime> getValueClass() {

    return ZonedDateTime.class;
  }

}
