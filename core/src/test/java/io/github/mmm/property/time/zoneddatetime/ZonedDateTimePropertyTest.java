package io.github.mmm.property.time.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.PropertyTest;
import io.github.mmm.property.time.zoneddatetime.ZonedDateTimeProperty;

/**
 * Test of {@link ZonedDateTimeProperty}.
 */
class ZonedDateTimePropertyTest extends PropertyTest<ZonedDateTime, ZonedDateTimeProperty> {

  ZonedDateTimePropertyTest() {

    super(ZonedDateTime.now(), ZonedDateTimeProperty.class);
  }

}
