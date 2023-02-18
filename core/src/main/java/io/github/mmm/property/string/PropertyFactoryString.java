/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link StringProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryString extends AbstractPropertyFactory<String, StringProperty> {

  @Override
  public Class<String> getValueClass() {

    return String.class;
  }

  @Override
  public Class<? extends ReadableProperty<String>> getReadableInterface() {

    return ReadableStringProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<String>> getWritableInterface() {

    return WritableStringProperty.class;
  }

  @Override
  public Class<StringProperty> getImplementationClass() {

    return StringProperty.class;
  }

  @Override
  public StringProperty create(String name, Class<? extends String> valueClass, PropertyMetadata<String> metadata,
      WritableProperty<?> valueProperty) {

    return new StringProperty(name, metadata);
  }

}
