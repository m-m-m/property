/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.pattern;

import java.util.regex.Pattern;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.property.string.StringProperty;

/**
 * Implementation of {@link WritablePatternProperty}.
 *
 * @since 1.0.0
 */
public class PatternProperty extends SimpleProperty<Pattern> implements WritablePatternProperty {

  private Pattern value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public PatternProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public PatternProperty(String name, PropertyMetadata<Pattern> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public PatternProperty(String name, Pattern value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public PatternProperty(String name, Pattern value, PropertyMetadata<Pattern> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Pattern doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Pattern newValue) {

    this.value = newValue;
  }

  @Override
  public Pattern parse(String valueAsString) {

    return Pattern.compile(valueAsString);
  }

  @Override
  public StringProperty asString() {

    return new PatternStringProperty(getName() + ".string");
  }

  private class PatternStringProperty extends StringProperty {

    /**
     * The constructor.
     *
     * @param name the {@link #getName() name}.
     */
    public PatternStringProperty(String name) {

      super(name);
    }

    @Override
    protected String doGet() {

      return PatternProperty.this.getAsString();
    }

    @Override
    protected void doSet(String newValue) {

      PatternProperty.this.set(Pattern.compile(newValue));
    }

  }

}
