package io.github.mmm.property.time;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.DurationInSecondsProperty;

/**
 * Test of {@link DurationInSecondsProperty}.
 */
class DurationInSecondsPropertyTest extends PropertyTest<Long, DurationInSecondsProperty> {

  DurationInSecondsPropertyTest() {

    super(1L, DurationInSecondsProperty.class, true);
  }

}
