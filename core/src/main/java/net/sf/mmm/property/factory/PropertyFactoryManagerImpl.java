/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.booleans.PropertyFactoryBoolean;
import net.sf.mmm.property.container.list.PropertyFactoryList;
import net.sf.mmm.property.container.map.PropertyFactoryMap;
import net.sf.mmm.property.container.set.PropertyFactorySet;
import net.sf.mmm.property.number.bytes.PropertyFactoryByte;
import net.sf.mmm.property.number.doubles.PropertyFactoryDouble;
import net.sf.mmm.property.number.floats.PropertyFactoryFloat;
import net.sf.mmm.property.number.integers.PropertyFactoryInteger;
import net.sf.mmm.property.number.longs.PropertyFactoryLong;
import net.sf.mmm.property.number.shorts.PropertyFactoryShort;
import net.sf.mmm.property.object.PropertyFactoryObject;
import net.sf.mmm.property.string.PropertyFactoryString;
import net.sf.mmm.property.temporal.instant.PropertyFactoryInstant;
import net.sf.mmm.property.temporal.localdate.PropertyFactoryLocalDate;
import net.sf.mmm.property.temporal.localdatetime.PropertyFactoryLocalDateTime;

/**
 * This is the implementation of {@link PropertyFactoryManager}.
 *
 * @since 1.0.0
 */
public class PropertyFactoryManagerImpl implements PropertyFactoryManager {

  private static PropertyFactoryManagerImpl instance;

  private final Map<Class<?>, PropertyFactory<?, ?>> propertyType2factoryMap;

  private final Map<Class<?>, PropertyFactory<?, ?>> valueType2factoryMap;

  private final List<PropertyFactory<?, ?>> polymorphicFactories;

  /**
   * The constructor.
   */
  public PropertyFactoryManagerImpl() {

    super();
    this.propertyType2factoryMap = new HashMap<>();
    this.valueType2factoryMap = new HashMap<>();
    this.polymorphicFactories = new ArrayList<>();
  }

  /**
   * @param factories the {@link List} of {@link PropertyFactory} instances to inject.
   */
  // @Inject
  public void setFactories(List<PropertyFactory<?, ?>> factories) {

    for (PropertyFactory<?, ?> factory : factories) {
      registerFactory(factory);
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
    if (valueClass == null) {
      Objects.requireNonNull(valueClass, factory.getClass().getName() + ".getValueClass()");
    }
    registerValueType(valueClass, factory, allowOverride);
    if (factory.isPolymorphic()) {
      registerPolymorphicFactory(factory);
    }
  }

  /**
   * @param factory
   */
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

  /**
   * Initializes this class. Sublcasses using CDI shall override and annotate this method with {@code @PostConstruct}.
   */
  // @PostConstruct
  protected void initialize() {

    if (this.propertyType2factoryMap.isEmpty()) {
      registerDefaults();
    }
    if (instance == null) {
      instance = this;
    }
  }

  /**
   * {@link #registerFactory(PropertyFactory) Registers} the {@link PropertyFactory factories} for the common default
   * types.
   */
  @SuppressWarnings("rawtypes")
  protected void registerDefaults() {

    registerFactory(new PropertyFactoryString());
    registerFactory(new PropertyFactoryObject());
    registerFactory(new PropertyFactoryBoolean());
    registerFactory(new PropertyFactoryDouble());
    registerFactory(new PropertyFactoryFloat());
    registerFactory(new PropertyFactoryInteger());
    registerFactory(new PropertyFactoryShort());
    registerFactory(new PropertyFactoryByte());
    registerFactory(new PropertyFactoryLong());
    registerFactory(new PropertyFactoryLocalDate());
    registerFactory(new PropertyFactoryLocalDateTime());
    registerFactory(new PropertyFactoryInstant());
    registerFactory(new PropertyFactoryList());
    registerFactory(new PropertyFactorySet());
    registerFactory(new PropertyFactoryMap());
    // registerFactory(new PropertyFactoryLink());
  }

  /**
   * This method gets the singleton instance of this {@link PropertyFactoryManager}. <br>
   *
   * @return the singleton instance.
   */
  @SuppressWarnings("rawtypes")
  public static PropertyFactoryManager getInstance() {

    if (instance == null) {
      synchronized (PropertyFactoryManagerImpl.class) {
        if (instance == null) {
          PropertyFactoryManagerImpl impl = new PropertyFactoryManagerImpl();
          List<PropertyFactory<?, ?>> factories = new ArrayList<>();
          ServiceLoader<PropertyFactory> serviceLoader = ServiceLoader.load(PropertyFactory.class);
          for (PropertyFactory factory : serviceLoader) {
            factories.add(factory);
          }
          if (factories.isEmpty()) {
            throw new IllegalStateException("No PropertyFactory available!");
          }
          impl.setFactories(factories);
          impl.initialize();
        }
      }
    }
    return instance;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <V, PROPERTY extends ReadableProperty<V>> PropertyFactory<V, ? extends PROPERTY> getFactoryForPropertyType(
      Class<PROPERTY> propertyType) {

    return (PropertyFactory) this.propertyType2factoryMap.get(propertyType);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <V> PropertyFactory<V, ? extends ReadableProperty<V>> getFactoryForValueType(Class<? extends V> valueClass) {

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

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public <V, PROPERTY extends ReadableProperty<V>> PROPERTY create(Class<PROPERTY> propertyType, Class<V> valueClass,
      String name, PropertyMetadata<V> metadata) {

    // Open/Oracle JDK compiler has so many bugs in handling of generics...
    PropertyFactory factory = getRequiredFactory((Class) propertyType, (Class) valueClass);
    return (PROPERTY) factory.create(name, valueClass, metadata);
  }

}
