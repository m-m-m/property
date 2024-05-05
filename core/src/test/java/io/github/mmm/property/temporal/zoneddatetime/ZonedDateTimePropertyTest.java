package io.github.mmm.property.temporal.zoneddatetime;

import java.time.ZonedDateTime;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link ZonedDateTimeProperty}.
 */
public class ZonedDateTimePropertyTest extends PropertyTest<ZonedDateTime, ZonedDateTimeProperty> {

  ZonedDateTimePropertyTest() {

    super(ZonedDateTime.now(), ZonedDateTimeProperty.class);
  }

}
