/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import java.util.regex.Pattern;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.pattern.PatternProperty;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.validation.pattern.ValidatorBuilderPattern;

/**
 * {@link PropertyBuilder} for {@link StringProperty}.
 *
 * @since 1.0.0
 */
public final class PatternPropertyBuilder extends
    PropertyBuilder<Pattern, PatternProperty, ValidatorBuilderPattern<PatternPropertyBuilder>, PatternPropertyBuilder> {

  /**
   * The constructor.
   *
   * @param lock the {@link #getLock() lock}.
   */
  public PatternPropertyBuilder(AttributeReadOnly lock) {

    super(lock);
  }

  @Override
  protected ValidatorBuilderPattern<PatternPropertyBuilder> createValidatorBuilder() {

    return new ValidatorBuilderPattern<>(this);
  }

  @Override
  protected PatternProperty build(String name, PropertyMetadata<Pattern> metadata) {

    return new PatternProperty(name, metadata);
  }

}
