package io.github.mmm.property.time.instant;

import java.time.Instant;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.instant.InstantProperty;

/**
 * Test of {@link InstantProperty}.
 */
class InstantPropertyTest extends PropertyTest<Instant, InstantProperty> {

  InstantPropertyTest() {

    super(Instant.now(), InstantProperty.class);
  }

}
