package io.github.mmm.property.temporal.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link OffsetDateTimeProperty}.
 */
public class OffsetDateTimePropertyTest extends PropertyTest<OffsetDateTime, OffsetDateTimeProperty> {

  OffsetDateTimePropertyTest() {

    super(OffsetDateTime.now(), OffsetDateTimeProperty.class);
  }

}
