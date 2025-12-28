package io.github.mmm.property.time.localdate;

import java.time.LocalDate;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.localdate.LocalDateProperty;

/**
 * Test of {@link LocalDateProperty}.
 */
class LocalDatePropertyTest extends PropertyTest<LocalDate, LocalDateProperty> {

  LocalDatePropertyTest() {

    super(LocalDate.now(), LocalDateProperty.class);
  }

}
