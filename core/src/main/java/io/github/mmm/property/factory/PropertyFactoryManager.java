/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.factory;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataNone;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;

/**
 * This is the interface for the manager where all {@link PropertyFactory} variants are registered.
 *
 * @since 1.0.0
 */
public interface PropertyFactoryManager {

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param <P> the generic type of the {@link WritableProperty property}.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  <V, P extends ReadableProperty<V>> PropertyFactory<V, ? extends P> getFactoryForPropertyType(Class<P> propertyType);

  /**
   * @see PropertyFactory#getValueClass()
   *
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#getValue() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  <V> PropertyFactory<V, ? extends ReadableProperty<V>> getFactoryForValueType(Class<? extends V> valueType);

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param <P> the generic type of the {@link WritableProperty property}.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#getValue() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <V, P extends ReadableProperty<V>> PropertyFactory<V, ? extends P> getFactory(Class<P> propertyType,
      Class<? extends V> valueType) {

    PropertyFactory factory = null;
    if (propertyType != null) {
      // Open/Oracle JDK compiler has so many bugs in handling of generics...
      factory = getFactoryForPropertyType((Class) propertyType);
    }
    if (valueType != null) {
      if ((factory == null) || (factory.getValueClass() == null)) {
        PropertyFactory<V, ? extends ReadableProperty<V>> valueFactory = getFactoryForValueType(valueType);
        if (valueFactory != null) {
          if ((propertyType == null) || (propertyType.isAssignableFrom(valueFactory.getImplementationClass()))) {
            factory = valueFactory;
          }
        }
      }
    }
    return factory;
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param <P> the generic type of the {@link WritableProperty property}.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#getValue() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <V, P extends ReadableProperty<V>> PropertyFactory<V, ? extends P> getRequiredFactory(Class<P> propertyType,
      Class<V> valueType) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics...
    PropertyFactory<V, ? extends P> factory = getFactory((Class) propertyType, (Class) valueType);
    if (factory == null) {
      Class<?> type = propertyType;
      if (type == null) {
        type = valueType;
      }
      throw new IllegalArgumentException("No PropertyFactory found for " + type);
    }
    return factory;
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param <P> the generic type of the {@link WritableProperty property} to create.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata TODO
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  default <V, P extends ReadableProperty<V>> P create(Class<P> propertyType, Class<V> valueClass, String name,
      PropertyMetadata<V> metadata) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics...
    PropertyFactory factory = getRequiredFactory((Class) propertyType, (Class) valueClass);
    return (P) factory.create(name, valueClass, metadata);
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param polymorphic - see {@link #getFactoryForValueType(Class)}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  default <V> WritableProperty<V> create(Class<V> valueClass, boolean polymorphic, String name) {

    return create(null, valueClass, name, PropertyMetadataNone.getInstance());
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param polymorphic - see {@link #getFactoryForValueType(Class)}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata the {@link ReadableProperty#getMetadata() metadata}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  default <V> WritableProperty<V> create(Class<V> valueClass, boolean polymorphic, String name,
      PropertyMetadata<V> metadata) {

    return create(null, valueClass, name, metadata);
  }

}