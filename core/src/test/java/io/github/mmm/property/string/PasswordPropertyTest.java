package io.github.mmm.property.string;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.WritableProperty;

/**
 * Test of {@link PasswordProperty}.
 */
class PasswordPropertyTest extends PropertyTest<String, PasswordProperty> {

  PasswordPropertyTest() {

    super("t0p$ecret", PasswordProperty.class, true);
  }

  @Override
  protected String expectedPropertyValueToString(WritableProperty<String> property) {

    return "**********";
  }

}
