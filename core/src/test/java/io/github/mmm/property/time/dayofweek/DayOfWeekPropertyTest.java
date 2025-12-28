package io.github.mmm.property.time.dayofweek;

import java.time.DayOfWeek;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link DayOfWeekProperty}.
 */
class DayOfWeekPropertyTest extends PropertyTest<DayOfWeek, DayOfWeekProperty> {

  DayOfWeekPropertyTest() {

    super(DayOfWeek.SUNDAY, DayOfWeekProperty.class);
  }

  @Override
  protected boolean isJsonEqualToString() {

    return false;
  }

}
