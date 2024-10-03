package io.github.mmm.property.time.month;

import java.time.Month;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link MonthProperty}.
 */
public class MonthPropertyTest extends PropertyTest<Month, MonthProperty> {

  MonthPropertyTest() {

    super(Month.FEBRUARY, MonthProperty.class);
  }

  @Override
  protected boolean isJsonEqualToString() {

    return false;
  }

}
