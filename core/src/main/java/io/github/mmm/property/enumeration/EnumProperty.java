/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.enumeration;

import java.util.Objects;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * Implementation of {@link WritableEnumProperty}.
 *
 * @param <E> type of {@link Enum} {@link #getValue() value}.
 * @since 1.0.0
 */
public class EnumProperty<E extends Enum<E>> extends SimpleProperty<E> implements WritableEnumProperty<E> {

  private Class<E> valueClass;

  private E value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public EnumProperty(String name) {

    this(name, null, (PropertyMetadata<E>) null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public EnumProperty(String name, PropertyMetadata<E> metadata) {

    this(name, metadata, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public EnumProperty(String name, E value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  @SuppressWarnings("unchecked")
  public EnumProperty(String name, E value, PropertyMetadata<E> metadata) {

    super(name, metadata);
    Objects.requireNonNull(value);
    this.value = value;
    this.valueClass = (Class<E>) this.value.getClass();
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   * @param valueClass the {@link #getValueClass() value class}.
   */
  public EnumProperty(String name, PropertyMetadata<E> metadata, Class<E> valueClass) {

    super(name, metadata);
    this.valueClass = valueClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Class<E> getValueClass() {

    if ((this.valueClass == null) && (this.value != null)) {
      this.valueClass = (Class<E>) this.value.getClass();
    }
    return this.valueClass;
  }

  @Override
  protected E doGet() {

    return this.value;
  }

  @Override
  protected void doSet(E newValue) {

    if ((this.valueClass != null) && (newValue != null)) {
      this.value = this.valueClass.cast(newValue);
    } else {
      this.value = newValue;
    }
  }

}
