/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.factory.PropertyFactory;
import io.github.mmm.property.factory.PropertyFactoryManager;
import io.github.mmm.property.factory.PropertyTypeInfo;
import io.github.mmm.property.factory.SimplePropertyFactory;

/**
 * This is the implementation of {@link PropertyFactoryManager}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryManagerImpl implements PropertyFactoryManager {

  /** The singleton instance. */
  public static final PropertyFactoryManagerImpl INSTANCE = new PropertyFactoryManagerImpl();

  private static final Map<Class<?>, Class<?>> PRIMITIVE2WRAPPER_MAP = Map.of( //
      boolean.class, Boolean.class, //
      int.class, Integer.class, //
      long.class, Long.class, //
      double.class, Double.class, //
      float.class, Float.class, //
      short.class, Short.class, //
      byte.class, Byte.class, //
      char.class, Character.class);

  private final Map<Class<?>, PropertyFactory<?, ?>> propertyType2factoryMap;

  private final Map<Class<?>, PropertyFactory<?, ?>> valueType2factoryMap;

  private final List<PropertyFactory<?, ?>> polymorphicFactories;

  /**
   * The constructor.
   */
  @SuppressWarnings("rawtypes")
  protected PropertyFactoryManagerImpl() {

    super();
    this.propertyType2factoryMap = new HashMap<>();
    this.valueType2factoryMap = new HashMap<>();
    this.polymorphicFactories = new ArrayList<>();
    ServiceLoader<PropertyFactory> serviceLoader = ServiceLoader.load(PropertyFactory.class);
    for (PropertyFactory<?, ?> factory : serviceLoader) {
      registerFactory(factory);
    }
    if (this.valueType2factoryMap.isEmpty()) {
      throw new IllegalStateException("No PropertyFactory available!");
    }
  }

  /**
   * @param factory the {@link PropertyFactory} to register.
   */
  public void registerFactory(PropertyFactory<?, ?> factory) {

    registerFactory(factory, false);
  }

  /**
   * @param factory the {@link PropertyFactory} to register.
   * @param allowOverride - {@code true} if the given {@link PropertyFactory} may override (replace) a previously
   *        {@link #registerFactory(PropertyFactory, boolean) registered} one.
   */
  protected void registerFactory(PropertyFactory<?, ?> factory, boolean allowOverride) {

    Class<?> readableInterface = factory.getReadableInterface();
    if ((readableInterface != null) && (readableInterface != ReadableProperty.class)) {
      registerPropertyType(readableInterface, factory, allowOverride);
    }
    Class<?> writableInterface = factory.getWritableInterface();
    if ((writableInterface != null) && (writableInterface != WritableProperty.class)) {
      registerPropertyType(writableInterface, factory, allowOverride);
    }
    Class<?> implementationClass = factory.getImplementationClass();
    if (implementationClass == null) {
      Objects.requireNonNull(implementationClass, factory.getClass().getName() + ".getImplementationClass()");
    }
    registerPropertyType(implementationClass, factory, allowOverride);
    Class<?> valueClass = factory.getValueClass();
    if (valueClass != null) {
      registerValueType(valueClass, factory, allowOverride);
    }
    if (factory.isPolymorphic()) {
      registerPolymorphicFactory(factory);
    }
  }

  private void registerPolymorphicFactory(PropertyFactory<?, ?> factory) {

    int index = 0;
    Class<?> valueClass = factory.getValueClass();
    for (int i = 0; i < this.polymorphicFactories.size(); i++) {
      PropertyFactory<?, ?> existingFactory = this.polymorphicFactories.get(i);
      if (valueClass.isAssignableFrom(existingFactory.getValueClass())) {
        index = i + 1;
      }
    }
    this.polymorphicFactories.add(index, factory);
  }

  private void registerValueType(Class<?> type, PropertyFactory<?, ?> factory, boolean allowOverride) {

    register(this.valueType2factoryMap, type, factory, allowOverride);
  }

  private void registerPropertyType(Class<?> type, PropertyFactory<?, ?> factory, boolean allowOverride) {

    register(this.propertyType2factoryMap, type, factory, allowOverride);
  }

  private static void register(Map<Class<?>, PropertyFactory<?, ?>> map, Class<?> type, PropertyFactory<?, ?> factory,
      boolean allowOverride) {

    if (type == null) {
      return;
    }
    PropertyFactory<?, ?> old = map.put(type, factory);
    if ((old != null) && !allowOverride) {
      throw new IllegalArgumentException(
          "Duplicate PojoFactory " + factory + " for " + type + " already having " + old);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <V, PROPERTY extends WritableProperty<V>> PropertyFactory<V, ? extends PROPERTY> getFactoryForPropertyType(
      Class<PROPERTY> propertyType) {

    PropertyFactory factory = this.propertyType2factoryMap.get(propertyType);
    if ((factory == null) && !Modifier.isAbstract(propertyType.getModifiers())) {
      factory = new SimplePropertyFactory(propertyType);
    }
    return factory;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <V> PropertyFactory<V, ? extends WritableProperty<V>> getFactoryForValueType(Class<? extends V> valueClass) {

    if (valueClass.isPrimitive()) {
      valueClass = (Class) PRIMITIVE2WRAPPER_MAP.get(valueClass);
    }
    PropertyFactory<?, ?> factory = this.valueType2factoryMap.get(valueClass);
    if (factory == null) {
      for (PropertyFactory<?, ?> polymorphicFactory : this.polymorphicFactories) {
        if (polymorphicFactory.getValueClass().isAssignableFrom(valueClass)) {
          factory = polymorphicFactory;
          break;
        }
      }
    }
    return (PropertyFactory) factory;
  }

  @Override
  public Set<Class<?>> getValueTypes() {

    return Collections.unmodifiableSet(this.valueType2factoryMap.keySet());
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public <V, PROPERTY extends WritableProperty<V>> PROPERTY create(Class<PROPERTY> propertyType,
      PropertyTypeInfo<V> typeInfo, String name, PropertyMetadata<V> metadata) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics...
    PropertyFactory factory = getRequiredFactory((Class) propertyType, (Class) typeInfo.getValueClass());
    return (PROPERTY) factory.create(name, typeInfo, metadata);
  }

}
