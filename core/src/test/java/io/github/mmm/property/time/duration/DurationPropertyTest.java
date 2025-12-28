package io.github.mmm.property.time.duration;

import java.time.Duration;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.duration.DurationProperty;

/**
 * Test of {@link DurationProperty}.
 */
class DurationPropertyTest extends PropertyTest<Duration, DurationProperty> {

  DurationPropertyTest() {

    super(Duration.ofSeconds(1), DurationProperty.class);
  }

}
