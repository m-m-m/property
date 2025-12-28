package io.github.mmm.property.locale;

import java.util.Locale;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link LocaleProperty}.
 */
class LocalePropertyTest extends PropertyTest<Locale, LocaleProperty> {

  LocalePropertyTest() {

    super(Locale.GERMAN, LocaleProperty.class);
  }

}
