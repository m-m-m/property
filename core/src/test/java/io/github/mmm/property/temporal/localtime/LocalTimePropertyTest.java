package io.github.mmm.property.temporal.localtime;

import java.time.LocalTime;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link LocalTimeProperty}.
 */
public class LocalTimePropertyTest extends PropertyTest<LocalTime, LocalTimeProperty> {

  LocalTimePropertyTest() {

    super(LocalTime.now(), LocalTimeProperty.class);
  }

}
