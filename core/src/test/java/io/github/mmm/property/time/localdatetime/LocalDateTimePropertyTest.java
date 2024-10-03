package io.github.mmm.property.time.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.localdatetime.LocalDateTimeProperty;

/**
 * Test of {@link LocalDateTimeProperty}.
 */
public class LocalDateTimePropertyTest extends PropertyTest<LocalDateTime, LocalDateTimeProperty> {

  LocalDateTimePropertyTest() {

    super(LocalDateTime.now(), LocalDateTimeProperty.class);
  }

}
