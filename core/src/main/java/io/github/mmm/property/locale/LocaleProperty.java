/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.locale;

import java.util.Locale;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableLocaleProperty}.
 *
 * @since 1.0.0
 */
public class LocaleProperty extends SimpleProperty<Locale> implements WritableLocaleProperty {

  private Locale value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public LocaleProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public LocaleProperty(String name, PropertyMetadata<Locale> metadata) {

    super(name, metadata);
  }

  @Override
  protected Locale doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Locale newValue) {

    this.value = newValue;
  }

}
