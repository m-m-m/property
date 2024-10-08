/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.observable.time.offsetdatetime.OffsetDateTimeExpression;

/**
 * {@link ReadableProperty} for {@link OffsetDateTime} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableOffsetDateTimeProperty extends ReadableProperty<OffsetDateTime>, OffsetDateTimeExpression {

  @Override
  default Class<OffsetDateTime> getValueClass() {

    return OffsetDateTime.class;
  }

}
