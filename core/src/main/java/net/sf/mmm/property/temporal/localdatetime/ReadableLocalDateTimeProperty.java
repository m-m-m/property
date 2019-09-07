/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.localdatetime;

import java.time.LocalDateTime;

import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.value.observable.temporal.localdatetime.LocalDateTimeExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link LocalDateTime}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableLocalDateTimeProperty extends ReadableProperty<LocalDateTime>, LocalDateTimeExpression {

  @Override
  default Class<LocalDateTime> getValueClass() {

    return LocalDateTime.class;
  }

}
