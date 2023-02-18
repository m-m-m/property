package io.github.mmm.property.locale;

import java.util.Locale;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link LocaleProperty}.
 */
public class LocalePropertyTest extends PropertyTest<Locale, LocaleProperty> {

  @Override
  protected Class<Locale> getValueClass() {

    return Locale.class;
  }

  @Override
  protected Locale getExampleValue() {

    return Locale.GERMAN;
  }

}
