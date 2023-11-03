/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.factory;

import java.util.Collection;
import java.util.Set;

import io.github.mmm.base.exception.ObjectNotFoundException;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.impl.PropertyFactoryManagerImpl;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;

/**
 * This is the interface for the manager where all {@link PropertyFactory} variants are registered.
 *
 * @since 1.0.0
 */
public interface PropertyFactoryManager {

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  default <V> WritableProperty<V> create(Class<V> valueClass, String name) {

    return create(null, valueClass, name);
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata the {@link ReadableProperty#getMetadata() metadata}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  default <V> WritableProperty<V> create(Class<V> valueClass, String name, PropertyMetadata<V> metadata) {

    return create(null, valueClass, name, metadata);
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param <P> the generic type of the {@link WritableProperty property} to create.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  default <V, P extends ReadableProperty<V>> P create(Class<P> propertyType, Class<V> valueClass, String name) {

    return create(propertyType, valueClass, name, PropertyMetadataNone.get());
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param <P> the generic type of the {@link WritableProperty property} to create.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata the {@link PropertyMetadata}.
   * @param valueProperty the optional {@link io.github.mmm.property.container.ContainerProperty#getValueProperty()
   *        value property}. Will typically be {@code null}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  default <V, P extends ReadableProperty<V>> P create(Class<P> propertyType, Class<V> valueClass, String name,
      PropertyMetadata<V> metadata) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics... https://github.com/m-m-m/util/issues/166
    PropertyFactory factory = getRequiredFactory(propertyType, valueClass);
    if (factory instanceof AbstractSimplePropertyFactory simpleFactory) {
      // avoid creating PropertyTypeInfo if not used...
      return (P) simpleFactory.create(name, metadata);
    } else {
      return (P) factory.create(name, PropertyTypeInfo.ofValueClass(valueClass), metadata);
    }
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param <P> the generic type of the {@link WritableProperty property} to create.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param typeInfo the {@link PropertyTypeInfo}.
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param metadata the {@link PropertyMetadata}.
   * @param valueProperty the optional {@link io.github.mmm.property.container.ContainerProperty#getValueProperty()
   *        value property}. Will typically be {@code null}.
   * @return the new instance of the property.
   * @throws IllegalArgumentException if no {@link PropertyFactory} was {@link #getFactoryForPropertyType(Class) found}
   *         for {@code propertyType}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  default <V, P extends ReadableProperty<V>> P create(Class<P> propertyType, PropertyTypeInfo<V> typeInfo, String name,
      PropertyMetadata<V> metadata) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics... https://github.com/m-m-m/util/issues/166
    PropertyFactory factory = getRequiredFactory(propertyType, typeInfo.getValueClass());
    return (P) factory.create(name, typeInfo, metadata);
  }

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
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
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#get() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  <V> PropertyFactory<V, ? extends ReadableProperty<V>> getFactoryForValueType(Class<? extends V> valueType);

  /**
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param <P> the generic type of the {@link WritableProperty property}.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#get() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <V, P extends ReadableProperty<V>> PropertyFactory<V, ? extends P> getFactory(Class<P> propertyType,
      Class<V> valueType) {

    PropertyFactory factory = null;
    if (propertyType != null) {
      // Open/Oracle JDK compiler has so many bugs in handling of generics... https://github.com/m-m-m/util/issues/166
      factory = getFactoryForPropertyType(propertyType);
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
   * @param <V> the generic type of the {@link WritableProperty#get() property value}.
   * @param <P> the generic type of the {@link WritableProperty property}.
   * @param propertyType the {@link Class} reflecting the property to create. May be the
   *        {@link PropertyFactory#getReadableInterface() readable interface},
   *        {@link PropertyFactory#getWritableInterface() writable interface}, or the
   *        {@link PropertyFactory#getImplementationClass() implementation}.
   * @param valueType the {@link Class} reflecting the {@link WritableProperty#get() property value}.
   * @return the according {@link PropertyFactory} or {@code null} if no such factory is registered.
   * @throws ObjectNotFoundException if no such {@link PropertyFactory} could be found.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <V, P extends ReadableProperty<V>> PropertyFactory<V, ? extends P> getRequiredFactory(Class<P> propertyType,
      Class<V> valueType) {

    PropertyFactory<V, ? extends P> factory = getFactory(propertyType, (Class) valueType);
    if (factory == null) {
      Class<?> type = propertyType;
      if (type == null) {
        type = valueType;
      }
      throw new ObjectNotFoundException("PropertyFactory", type);
    }
    return factory;
  }

  /**
   * @return a {@link Collection} with all registered {@link ReadableProperty#getValueClass() value types}. So for all
   *         the returned {@link Class}es {@link #create(Class, String) creation of property by value type} is
   *         supported.
   */
  Set<Class<?>> getValueTypes();

  /**
   * @return the {@link PropertyFactoryManager} instance.
   */
  static PropertyFactoryManager get() {

    return PropertyFactoryManagerImpl.INSTANCE;
  }

}
