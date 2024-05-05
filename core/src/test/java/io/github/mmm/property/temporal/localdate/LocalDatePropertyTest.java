package io.github.mmm.property.temporal.localdate;

import java.time.LocalDate;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link LocalDateProperty}.
 */
public class LocalDatePropertyTest extends PropertyTest<LocalDate, LocalDateProperty> {

  LocalDatePropertyTest() {

    super(LocalDate.now(), LocalDateProperty.class);
  }

}
