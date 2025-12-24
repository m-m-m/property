/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import java.util.Objects;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;

/**
 * Generic implementation of {@link WritableProperty} for arbitrary objects that do not have their own custom
 * implementation.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public class ObjectProperty<V> extends SimpleProperty<V> implements WritableObjectProperty<V> {

  private final Class<V> valueClass;

  private V value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   */
  public ObjectProperty(String name, Class<V> valueClass) {

    this(name, valueClass, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ObjectProperty(String name, Class<V> valueClass, PropertyMetadata<V> metadata) {

    this(name, valueClass, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public ObjectProperty(String name, V value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ObjectProperty(String name, V value, PropertyMetadata<V> metadata) {

    this(name, null, value, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  @SuppressWarnings("unchecked")
  protected ObjectProperty(String name, Class<V> valueClass, V value, PropertyMetadata<V> metadata) {

    super(name, metadata);
    if (valueClass == null) {
      Objects.requireNonNull(value);
      this.valueClass = (Class<V>) value.getClass();
    } else {
      this.valueClass = valueClass;
    }
    if (value != null) {
      doSet(value);
    }
  }

  @Override
  public Class<V> getValueClass() {

    return this.valueClass;
  }

  @Override
  protected V doGet() {

    return this.value;
  }

  @Override
  protected void doSet(V newValue) {

    assert (this.valueClass.isInstance(newValue));
    this.value = newValue;
  }

  @Override
  public V parse(String valueAsString) {

    throw new UnsupportedOperationException();
  }

  @Override
  public V getFallbackSafeValue() {

    return null; // no generic fallback available
  }

}
