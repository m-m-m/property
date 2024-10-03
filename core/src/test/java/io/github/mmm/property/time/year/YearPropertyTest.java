package io.github.mmm.property.time.year;

import java.time.Year;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link YearProperty}.
 */
public class YearPropertyTest extends PropertyTest<Year, YearProperty> {

  YearPropertyTest() {

    super(Year.of(1999), YearProperty.class);
  }

}
