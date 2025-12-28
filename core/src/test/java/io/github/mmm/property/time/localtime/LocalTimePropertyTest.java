package io.github.mmm.property.time.localtime;

import java.time.LocalTime;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.localtime.LocalTimeProperty;

/**
 * Test of {@link LocalTimeProperty}.
 */
class LocalTimePropertyTest extends PropertyTest<LocalTime, LocalTimeProperty> {

  LocalTimePropertyTest() {

    super(LocalTime.now(), LocalTimeProperty.class);
  }

}
