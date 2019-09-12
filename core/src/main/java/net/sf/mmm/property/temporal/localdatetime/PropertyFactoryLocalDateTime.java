/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.localdatetime;

import java.time.LocalDateTime;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LocalDateTimeProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
public class PropertyFactoryLocalDateTime extends AbstractPropertyFactory<LocalDateTime, LocalDateTimeProperty> {

  @Override
  public Class<LocalDateTime> getValueClass() {

    return LocalDateTime.class;
  }

  @Override
  public Class<? extends ReadableProperty<LocalDateTime>> getReadableInterface() {

    return ReadableLocalDateTimeProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<LocalDateTime>> getWritableInterface() {

    return WritableLocalDateTimeProperty.class;
  }

  @Override
  public Class<LocalDateTimeProperty> getImplementationClass() {

    return LocalDateTimeProperty.class;
  }

  @Override
  public LocalDateTimeProperty create(String name, Class<? extends LocalDateTime> valueClass,
      PropertyMetadata<LocalDateTime> metadata) {

    return new LocalDateTimeProperty(name, metadata);
  }

}