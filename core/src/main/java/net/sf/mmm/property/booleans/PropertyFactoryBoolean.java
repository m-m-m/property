/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.booleans;

import javax.inject.Named;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link BooleanProperty}.
 *
 * @since 1.0.0
 */
@Named
public class PropertyFactoryBoolean extends AbstractPropertyFactory<Boolean, BooleanProperty> {

  @Override
  public Class<? extends Boolean> getValueClass() {

    return Boolean.class;
  }

  @Override
  public Class<? extends ReadableProperty<Boolean>> getReadableInterface() {

    return ReadableBooleanProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Boolean>> getWritableInterface() {

    return WritableBooleanProperty.class;
  }

  @Override
  public Class<BooleanProperty> getImplementationClass() {

    return BooleanProperty.class;
  }

  @Override
  public BooleanProperty create(String name, Class<? extends Boolean> valueClass, PropertyMetadata<Boolean> metadata) {

    return new BooleanProperty(name, metadata);
  }

}
