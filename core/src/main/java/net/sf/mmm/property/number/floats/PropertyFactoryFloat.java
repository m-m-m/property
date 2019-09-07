/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.floats;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link FloatProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryFloat extends AbstractPropertyFactory<Float, FloatProperty> {

  @Override
  public Class<Float> getValueClass() {

    return Float.class;
  }

  @Override
  public Class<? extends ReadableProperty<Float>> getReadableInterface() {

    return ReadableFloatProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Float>> getWritableInterface() {

    return WritableFloatProperty.class;
  }

  @Override
  public Class<FloatProperty> getImplementationClass() {

    return FloatProperty.class;
  }

  @Override
  public FloatProperty create(String name, Class<? extends Float> valueClass, PropertyMetadata<Float> metadata) {

    return new FloatProperty(name, metadata);
  }

}
