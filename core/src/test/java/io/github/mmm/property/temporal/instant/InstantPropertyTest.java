package io.github.mmm.property.temporal.instant;

import java.time.Instant;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link InstantProperty}.
 */
public class InstantPropertyTest extends PropertyTest<Instant, InstantProperty> {

  InstantPropertyTest() {

    super(Instant.now(), InstantProperty.class);
  }

}
