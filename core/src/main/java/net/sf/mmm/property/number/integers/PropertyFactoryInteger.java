/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number.integers;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link IntegerProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryInteger extends AbstractPropertyFactory<Integer, IntegerProperty> {

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
  public IntegerProperty create(String name, Class<? extends Integer> valueClass, PropertyMetadata<Integer> metadata) {

    return new IntegerProperty(name, metadata);
  }

}
