/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.container.ContainerProperty;
import io.github.mmm.value.observable.container.map.ChangeAwareMap;

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

  private ChangeAwareMap<K, V> changeAwareMap;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public MapProperty(String name, Class<V> componentClass, Type componentType) {

    super(name, componentClass, componentType);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MapProperty(String name, Class<V> componentClass, Type componentType, PropertyMetadata<Map<K, V>> metadata) {

    super(name, componentClass, componentType, metadata);
  }

  @Override
  protected Map<K, V> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Map<K, V> newValue) {

    this.value = newValue;
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareMap != null);
  }

  @Override
  public ChangeAwareMap<K, V> getChangeAwareValue() {

    return this.changeAwareMap;
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
    if (isChangeAware()) {
      map = getChangeAwareValue();
    } else {
      map = getOrCreateValue();
    }
    do {
      K key = readKey(reader);
      V mapValue = readValue(reader);
      map.put(key, mapValue);
    } while (!reader.readEnd());
  }

  /**
   * Implementation of {@link #read(StructuredReader)} for a {@link Map#containsKey(Object) map key}.
   *
   * @param reader the {@link StructuredReader}.
   * @return the unmarshalled key.
   */
  @SuppressWarnings("unchecked")
  protected K readKey(StructuredReader reader) {

    // TODO
    return (K) reader.readName();
  }

  /**
   * Implementation of {@link #read(StructuredReader)} for a {@link Map#containsValue(Object) map value}.
   *
   * @param reader the {@link StructuredReader}.
   * @return the unmarshalled value.
   */
  protected V readValue(StructuredReader reader) {

    return reader.readValue(getComponentClass());
  }

  @Override
  public void write(StructuredWriter writer) {

    Map<K, V> map = getValue();
    if (map == null) {
      return;
    }
    writer.writeStartObject();
    for (Entry<K, V> entry : map.entrySet()) {
      writeKey(writer, entry.getKey());
      writeValue(writer, entry.getValue());
    }
    writer.writeEnd();
  }

  /**
   * Implementation of {@link #write(StructuredWriter)} for a {@link Map#containsKey(Object) map key}.
   *
   * @param writer the {@link StructuredWriter}.
   * @param key the {@link Map#containsKey(Object) map key} to marshall.
   */
  protected void writeKey(StructuredWriter writer, K key) {

    writer.writeName(Objects.toString(key));
  }

  /**
   * Implementation of {@link #write(StructuredWriter)} for a {@link Map#containsValue(Object) map value}.
   *
   * @param writer the {@link StructuredWriter}.
   * @param mapValue the {@link Map#containsValue(Object) map value} to marshall.
   */
  protected void writeValue(StructuredWriter writer, V mapValue) {

    writer.writeValue(mapValue);
  }

  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // @Override
  // public AbstractMapValidatorBuilder<K, V, Map<K, V>, PropertyBuilder<MapProperty<K, V>>, ?> withValdidator() {
  //
  // Function factory = new Function<PropertyBuilder<MapProperty<K, V>>, ValidatorBuilderMap<K, V,
  // PropertyBuilder<MapProperty<K, V>>>>() {
  //
  // @Override
  // public ValidatorBuilderMap<K, V, PropertyBuilder<MapProperty<K, V>>> apply(PropertyBuilder<MapProperty<K, V>> t) {
  //
  // return new ValidatorBuilderMap<>(t);
  // }
  // };
  // return (ValidatorBuilderMap) withValdidator(factory);
  // }

}
