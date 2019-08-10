/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.localdates;

import java.time.LocalDate;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.value.observable.temporals.localdates.LocalDateExpression;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link LocalDate}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableLocalDateProperty extends ReadableProperty<LocalDate>, LocalDateExpression {

  @Override
  default Class<LocalDate> getValueClass() {

    return LocalDate.class;
  }

}
