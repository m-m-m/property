/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.shorts;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link ShortProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryShort extends AbstractPropertyFactory<Short, ShortProperty> {

  @Override
  public Class<Short> getValueClass() {

    return Short.class;
  }

  @Override
  public Class<? extends ReadableProperty<Short>> getReadableInterface() {

    return ReadableShortProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Short>> getWritableInterface() {

    return WritableShortProperty.class;
  }

  @Override
  public Class<ShortProperty> getImplementationClass() {

    return ShortProperty.class;
  }

  @Override
  public ShortProperty create(String name, Class<? extends Short> valueClass, PropertyMetadata<Short> metadata) {

    return new ShortProperty(name, metadata);
  }

}
