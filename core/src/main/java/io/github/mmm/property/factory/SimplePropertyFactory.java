/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.factory;

import java.lang.reflect.Constructor;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;

/**
 * A simple generic implementation of {@link PropertyFactory} used as dynamic fallback for custom property
 * implementations without having a proper factory registered.
 *
 * @param <V> type of the {@link WritableProperty}.
 * @since 1.0.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SimplePropertyFactory<V> extends AbstractPropertyFactory<V, WritableProperty<V>> {

  private final Class<WritableProperty<V>> impl;

  private final WritableProperty<V> prototype;

  /**
   * The constructor.
   *
   * @param impl the {@link #getImplementationClass() implementation class} of the property.
   */
  public SimplePropertyFactory(Class<WritableProperty<V>> impl) {

    super();
    this.impl = impl;
    try {
      Constructor<WritableProperty<V>> constructor = impl.getConstructor(String.class, PropertyMetadata.class);
      this.prototype = constructor.newInstance("Name", null);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public Class<? extends V> getValueClass() {

    return null;
  }

  @Override
  public Class<? extends ReadableProperty<V>> getReadableInterface() {

    return (Class) ReadableProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<V>> getWritableInterface() {

    return (Class) WritableProperty.class;
  }

  @Override
  public Class<WritableProperty<V>> getImplementationClass() {

    return this.impl;
  }

  @Override
  public WritableProperty<V> create(String name, Class<? extends V> valueClass, PropertyMetadata<V> metadata) {

    return this.prototype.copy(name, metadata);
  }

}
