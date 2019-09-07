/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.number.longs.LongProperty;

/**
 * Extends {@link LongProperty} to store a {@link Duration} in seconds.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class DurationInSecondsProperty extends LongProperty {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public DurationInSecondsProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public DurationInSecondsProperty(String name, PropertyMetadata<Long> metadata) {

    super(name, metadata);
  }

  /**
   * @return the {@link #getValue() value} as {@link Duration}.
   */
  public Duration getValueAsDuration() {

    Number value = getValue();
    if (value == null) {
      return null;
    }
    return Duration.ofSeconds(value.longValue());
  }

  /**
   * @param duration the new {@link #getValue() value} as {@link Duration}.
   */
  public void setValueAsDuration(Duration duration) {

    if (duration == null) {
      setValue(null);
    } else {
      setValue(Long.valueOf(duration.get(ChronoUnit.SECONDS)));
    }
  }

}
