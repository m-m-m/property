/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.maps;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.property.api.containers.ContainerProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.collection.AbstractMapValidatorBuilder;
import net.sf.mmm.util.validation.base.collection.ValidatorBuilderMap;

/**
 * Implementation of {@link WritableMapProperty}.
 *
 * @param <K> type of the {@link Map#containsKey(Object) keys}.
 * @param <V> type of the {@link Map#containsValue(Object) values}.
 *
 * @since 1.0.0
 */
public class MapProperty<K, V> extends ContainerProperty<Map<K, V>, V> implements WritableMapProperty<K, V> {

  private Map<K, V> value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public MapProperty(String name, Class<V> componentClass, Type componentType, Object bean) {

    super(name, componentClass, componentType, bean);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param validator - see {@link #validate()}.
   */
  public MapProperty(String name, Class<V> componentClass, Type componentType, Object bean,
      AbstractValidator<? super Map<K, V>> validator) {

    super(name, componentClass, componentType, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public MapProperty(String name, Class<V> componentClass, Type componentType, Object bean,
      Supplier<? extends Map<K, V>> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  protected Map<K, V> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Map<K, V> newValue) {

    this.value = newValue;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public AbstractMapValidatorBuilder<K, V, Map<K, V>, PropertyBuilder<MapProperty<K, V>>, ?> withValdidator() {

    Function factory = new Function<PropertyBuilder<MapProperty<K, V>>, ValidatorBuilderMap<K, V, PropertyBuilder<MapProperty<K, V>>>>() {

      @Override
      public ValidatorBuilderMap<K, V, PropertyBuilder<MapProperty<K, V>>> apply(PropertyBuilder<MapProperty<K, V>> t) {

        return new ValidatorBuilderMap<>(t);
      }
    };
    return (ValidatorBuilderMap) withValdidator(factory);
  }

  @Override
  public void read(StructuredReader reader) {

    Map<K, V> map;
    if (!reader.readStartObject()) {
      map = getValue();
      if (map != null) {
        map.clear();
      }
      return;
    }
    map = getOrCreateValue();
    Class<V> componentClass = getComponentClass();
    do {
      String name = reader.readName();
      K key = parseKey(name);
      V mapValue = reader.readValue(componentClass);
      map.put(key, mapValue);
    } while (!reader.readEnd());
  }

  @SuppressWarnings("unchecked")
  private K parseKey(String name) {

    // TODO
    return (K) name;
  }

  @Override
  public void write(StructuredWriter writer) {

    Map<K, V> map = getValue();
    if (map == null) {
      return;
    }
    writer.writeStartObject();
    for (Entry<K, V> entry : map.entrySet()) {
      writer.writeName(Objects.toString(entry.getKey()));
      writer.writeValue(entry.getValue());
    }
    writer.writeEnd();
  }

}
