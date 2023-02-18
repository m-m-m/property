/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.doubles;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link DoubleProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryDouble extends AbstractPropertyFactory<Double, DoubleProperty> {

  @Override
  public Class<Double> getValueClass() {

    return Double.class;
  }

  @Override
  public Class<? extends ReadableProperty<Double>> getReadableInterface() {

    return ReadableDoubleProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Double>> getWritableInterface() {

    return WritableDoubleProperty.class;
  }

  @Override
  public Class<DoubleProperty> getImplementationClass() {

    return DoubleProperty.class;
  }

  @Override
  public DoubleProperty create(String name, Class<? extends Double> valueClass, PropertyMetadata<Double> metadata,
      WritableProperty<?> valueProperty) {

    return new DoubleProperty(name, metadata);
  }

}
