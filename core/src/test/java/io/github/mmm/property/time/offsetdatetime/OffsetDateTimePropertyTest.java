package io.github.mmm.property.time.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.offsetdatetime.OffsetDateTimeProperty;

/**
 * Test of {@link OffsetDateTimeProperty}.
 */
public class OffsetDateTimePropertyTest extends PropertyTest<OffsetDateTime, OffsetDateTimeProperty> {

  OffsetDateTimePropertyTest() {

    super(OffsetDateTime.now(), OffsetDateTimeProperty.class);
  }

}
