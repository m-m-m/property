/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.factory;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;

/**
 * The abstract base implementation of {@link PropertyFactory}.
 *
 * @param <V> the generic type of the {@link WritableProperty#get() property value}.
 * @param <P> the generic type of the {@link WritableProperty property}.
 *
 * @since 1.0.0
 */
public abstract class AbstractSimplePropertyFactory<V, P extends WritableProperty<V>>
    extends AbstractPropertyFactory<V, P> {

  @Override
  public final P create(String name, Class<V> valueClass, PropertyMetadata<V> metadata) {

    return create(name, metadata);
  }

  @Override
  public final P create(String name, PropertyTypeInfo<V> valueClass, PropertyMetadata<V> metadata) {

    return create(name, metadata);
  }

  /**
   * Internal stable and simple API for simple properties that do not require generic {@link PropertyTypeInfo}.
   *
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata the {@link ReadableProperty#getMetadata() metadata}.
   * @return the new instance of the property.
   */
  protected abstract P create(String name, PropertyMetadata<V> metadata);

}
