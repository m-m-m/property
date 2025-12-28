package io.github.mmm.property.string;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link StringProperty}.
 */
class StringPropertyTest extends PropertyTest<String, StringProperty> {

  StringPropertyTest() {

    super("magic value", StringProperty.class);
  }

}
