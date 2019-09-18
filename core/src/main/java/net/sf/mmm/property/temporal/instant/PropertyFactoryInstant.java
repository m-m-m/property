/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.temporal.instant;

import java.time.Instant;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * This is the implementation of {@link PropertyFactory} for {@link InstantProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryInstant extends AbstractPropertyFactory<Instant, InstantProperty> {

  @Override
  public Class<Instant> getValueClass() {

    return Instant.class;
  }

  @Override
  public Class<? extends ReadableProperty<Instant>> getReadableInterface() {

    return ReadableInstantProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Instant>> getWritableInterface() {

    return WritableInstantProperty.class;
  }

  @Override
  public Class<InstantProperty> getImplementationClass() {

    return InstantProperty.class;
  }

  @Override
  public InstantProperty create(String name, Class<? extends Instant> valueClass, PropertyMetadata<Instant> metadata) {

    return new InstantProperty(name, metadata);
  }

}
