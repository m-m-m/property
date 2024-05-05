package io.github.mmm.property.temporal.duration;

import java.time.Duration;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link DurationProperty}.
 */
public class DurationPropertyTest extends PropertyTest<Duration, DurationProperty> {

  DurationPropertyTest() {

    super(Duration.ofSeconds(1), DurationProperty.class);
  }

}
