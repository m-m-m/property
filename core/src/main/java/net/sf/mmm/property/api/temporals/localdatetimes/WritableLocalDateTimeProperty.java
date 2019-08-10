/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.localdatetimes;

import java.time.LocalDateTime;

import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.value.observable.temporals.localdatetimes.WritableLocalDateTimeValue;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getValueClass() type}
 * {@link LocalDateTime}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableLocalDateTimeProperty
    extends ReadableLocalDateTimeProperty, WritableProperty<LocalDateTime>, WritableLocalDateTimeValue {

}
