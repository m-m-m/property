/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.booleans;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link BooleanProperty}.
 *
 * @since 1.0.0
 */
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
  public BooleanProperty create(String name, Class<? extends Boolean> valueClass, PropertyMetadata<Boolean> metadata,
      WritableProperty<?> valueProperty) {

    return new BooleanProperty(name, metadata);
  }

}
