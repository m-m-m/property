/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number.integers;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link IntegerProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryInteger extends AbstractSimplePropertyFactory<Integer, IntegerProperty> {

  @Override
  public Class<Integer> getValueClass() {

    return Integer.class;
  }

  @Override
  public Class<? extends ReadableProperty<Integer>> getReadableInterface() {

    return ReadableIntegerProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Integer>> getWritableInterface() {

    return WritableIntegerProperty.class;
  }

  @Override
  public Class<IntegerProperty> getImplementationClass() {

    return IntegerProperty.class;
  }

  @Override
  public IntegerProperty create(String name, PropertyMetadata<Integer> metadata) {

    return new IntegerProperty(name, metadata);
  }

}
