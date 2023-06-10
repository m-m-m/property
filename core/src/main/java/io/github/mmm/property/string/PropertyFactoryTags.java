/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractPropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link TagsProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryTags extends AbstractPropertyFactory<String, TagsProperty> {

  @Override
  public Class<String> getValueClass() {

    return String.class;
  }

  @Override
  public Class<? extends ReadableProperty<String>> getReadableInterface() {

    return ReadableTagsProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<String>> getWritableInterface() {

    return WritableTagsProperty.class;
  }

  @Override
  public Class<TagsProperty> getImplementationClass() {

    return TagsProperty.class;
  }

  @Override
  public TagsProperty create(String name, Class<? extends String> valueClass, PropertyMetadata<String> metadata,
      WritableProperty<?> valueProperty) {

    return new TagsProperty(name, metadata);
  }

}
