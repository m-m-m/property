/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.object;

import java.lang.reflect.Type;
import java.util.Objects;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataNone;
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

  private final Type valueType;

  private V value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   */
  public ObjectProperty(String name, Class<V> valueClass) {

    this(name, valueClass, PropertyMetadataNone.getInstance());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ObjectProperty(String name, Class<V> valueClass, PropertyMetadata<V> metadata) {

    super(name, metadata);
    Objects.requireNonNull(valueClass);
    this.valueClass = valueClass;
    Type type = metadata.getValueType();
    if (type == null) {
      type = valueClass;
    }
    this.valueType = type;
  }

  @Override
  public Class<V> getValueClass() {

    return this.valueClass;
  }

  @Override
  public Type getValueType() {

    return this.valueType;
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

}
