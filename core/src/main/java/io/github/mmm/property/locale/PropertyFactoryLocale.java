/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.locale;

import java.util.Locale;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link LocaleProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryLocale extends AbstractPropertyFactory<Locale, LocaleProperty> {

  @Override
  public Class<Locale> getValueClass() {

    return Locale.class;
  }

  @Override
  public Class<? extends ReadableProperty<Locale>> getReadableInterface() {

    return ReadableLocaleProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Locale>> getWritableInterface() {

    return WritableLocaleProperty.class;
  }

  @Override
  public Class<LocaleProperty> getImplementationClass() {

    return LocaleProperty.class;
  }

  @Override
  public LocaleProperty create(String name, Class<? extends Locale> valueClass, PropertyMetadata<Locale> metadata,
      WritableProperty<?> valueProperty) {

    return new LocaleProperty(name, metadata);
  }

}
