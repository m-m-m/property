/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.pattern;

import java.util.regex.Pattern;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.AbstractSimplePropertyFactory;
import io.github.mmm.property.factory.PropertyFactory;

/**
 * Implementation of {@link PropertyFactory} for {@link PatternProperty}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryPattern extends AbstractSimplePropertyFactory<Pattern, PatternProperty> {

  @Override
  public Class<Pattern> getValueClass() {

    return Pattern.class;
  }

  @Override
  public Class<? extends ReadableProperty<Pattern>> getReadableInterface() {

    return ReadablePatternProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Pattern>> getWritableInterface() {

    return WritablePatternProperty.class;
  }

  @Override
  public Class<PatternProperty> getImplementationClass() {

    return PatternProperty.class;
  }

  @Override
  public PatternProperty create(String name, PropertyMetadata<Pattern> metadata) {

    return new PatternProperty(name, metadata);
  }

}
