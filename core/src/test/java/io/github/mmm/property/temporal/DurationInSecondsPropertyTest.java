package io.github.mmm.property.temporal;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link DurationInSecondsProperty}.
 */
public class DurationInSecondsPropertyTest extends PropertyTest<Long, DurationInSecondsProperty> {

  DurationInSecondsPropertyTest() {

    super(1L, DurationInSecondsProperty.class, true);
  }

}
