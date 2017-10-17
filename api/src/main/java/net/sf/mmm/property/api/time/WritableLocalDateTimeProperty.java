/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.time;

import java.time.LocalDateTime;

import net.sf.mmm.property.api.WritableProperty;

/**
 * This is the interface for a {@link WritableProperty} of the {@link #getValue() value}-{@link #getType() type}
 * {@link LocalDateTime}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableLocalDateTimeProperty
    extends ReadableLocalDateTimeProperty, WritableProperty<LocalDateTime> {

}
