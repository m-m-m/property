/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.string;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;

/**
 * Implementation of {@link WritableStringProperty}.
 *
 * @since 1.0.0
 */
public class StringProperty extends Property<String> implements WritableStringProperty {

  private String value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
  }

  @Override
  protected String doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(String newValue) {

    this.value = newValue;
  }

  // @Override
  // public ValidatorBuilderString<PropertyBuilder<StringProperty>> withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderString<>(x));
  // }

}
