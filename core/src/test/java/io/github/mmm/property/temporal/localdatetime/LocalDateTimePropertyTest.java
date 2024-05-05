package io.github.mmm.property.temporal.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link LocalDateTimeProperty}.
 */
public class LocalDateTimePropertyTest extends PropertyTest<LocalDateTime, LocalDateTimeProperty> {

  LocalDateTimePropertyTest() {

    super(LocalDateTime.now(), LocalDateTimeProperty.class);
  }

}
