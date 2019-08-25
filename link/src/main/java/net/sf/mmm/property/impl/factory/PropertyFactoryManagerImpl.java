/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.impl.factory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.booleans.PropertyFactoryBoolean;
import net.sf.mmm.property.api.containers.lists.PropertyFactoryList;
import net.sf.mmm.property.api.containers.maps.PropertyFactoryMap;
import net.sf.mmm.property.api.containers.sets.PropertyFactorySet;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactoryManager;
import net.sf.mmm.property.api.numbers.bytes.PropertyFactoryByte;
import net.sf.mmm.property.api.numbers.doubles.PropertyFactoryDouble;
import net.sf.mmm.property.api.numbers.floats.PropertyFactoryFloat;
import net.sf.mmm.property.api.numbers.integers.PropertyFactoryInteger;
import net.sf.mmm.property.api.numbers.longs.PropertyFactoryLong;
import net.sf.mmm.property.api.numbers.shorts.PropertyFactoryShort;
import net.sf.mmm.property.api.objects.PropertyFactoryObject;
import net.sf.mmm.property.api.strings.PropertyFactoryString;
import net.sf.mmm.property.api.temporals.instants.PropertyFactoryInstant;
import net.sf.mmm.property.api.temporals.localdates.PropertyFactoryLocalDate;
import net.sf.mmm.property.api.temporals.localdatetimes.PropertyFactoryLocalDateTime;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactoryManager}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class PropertyFactoryManagerImpl implements PropertyFactoryManager {

  private static PropertyFactoryManagerImpl instance;

  private final Map<Class<?>, PropertyFactory<?, ?>> propertyType2factoryMap;

  private final Map<Class<?>, PropertyFactory<?, ?>> valueType2factoryMap;

  /**
   * The constructor.
   */
  public PropertyFactoryManagerImpl() {

    super();
    this.propertyType2factoryMap = new HashMap<>();
    this.valueType2factoryMap = new HashMap<>();
  }

  /**
   * @param factories the {@link List} of {@link PropertyFactory} instances to {@link Inject}.
   */
  @Inject
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

    registerPropertyType(factory.getReadableInterface(), factory, allowOverride);
    registerPropertyType(factory.getWritableInterface(), factory, allowOverride);
    registerPropertyType(factory.getImplementationClass(), factory, allowOverride);
    registerValueType(factory.getValueClass(), factory, allowOverride);
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
  public static PropertyFactoryManager getInstance() {

    if (instance == null) {
      synchronized (PropertyFactoryManagerImpl.class) {
        if (instance == null) {
          PropertyFactoryManagerImpl impl = new PropertyFactoryManagerImpl();
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
  public <V> PropertyFactory<V, ? extends ReadableProperty<V>> getFactoryForValueType(Class<? extends V> valueType,
      boolean polymorphic) {

    PropertyFactory<?, ?> factory = this.valueType2factoryMap.get(valueType);
    if ((factory == null) && polymorphic) {
      for (Entry<Class<?>, PropertyFactory<?, ?>> entry : this.valueType2factoryMap.entrySet()) {
        if (entry.getKey().isAssignableFrom(valueType)) {
          factory = entry.getValue();
          break;
        }
      }
    }
    return (PropertyFactory) factory;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public <V, PROPERTY extends ReadableProperty<V>> PROPERTY create(Class<PROPERTY> propertyType, Class<V> valueClass,
      Type valueType, boolean polymorphic, String name, Object bean, AbstractValidator<? super V> validator) {

    PropertyFactory factory = getRequiredFactory(propertyType, valueClass, polymorphic);
    return (PROPERTY) factory.create(name, valueClass, bean, validator);
  }

}
