package io.github.mmm.property.number.shorts;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link ShortProperty}.
 */
class ShortPropertyTest extends PropertyTest<Short, ShortProperty> {

  ShortPropertyTest() {

    super(Short.valueOf((short) 1), ShortProperty.class);
  }

}
