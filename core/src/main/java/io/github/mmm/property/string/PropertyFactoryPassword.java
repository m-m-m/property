/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link StringProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryPassword extends AbstractSimplePropertyFactory<String, PasswordProperty> {

  @Override
  public Class<String> getValueClass() {

    return null;
  }

  @Override
  public Class<? extends ReadableProperty<String>> getReadableInterface() {

    return ReadablePasswordProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<String>> getWritableInterface() {

    return WritablePasswordProperty.class;
  }

  @Override
  public Class<PasswordProperty> getImplementationClass() {

    return PasswordProperty.class;
  }

  @Override
  public PasswordProperty create(String name, PropertyMetadata<String> metadata) {

    return new PasswordProperty(name, metadata);
  }

}
