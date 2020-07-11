/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataNone;
import io.github.mmm.property.container.ContainerProperty;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultBuilder;
import io.github.mmm.value.observable.container.map.ChangeAwareMap;
import io.github.mmm.value.observable.container.map.ChangeAwareMaps;

/**
 * Implementation of {@link WritableMapProperty}.
 *
 * @param <K> type of the {@link Map#containsKey(Object) keys}.
 * @param <V> type of the {@link Map#containsValue(Object) values}.
 *
 * @since 1.0.0
 */
public class MapProperty<K, V> extends ContainerProperty<Map<K, V>, V> implements WritableMapProperty<K, V> {

  private final SimpleProperty<K> keyProperty;

  private Map<K, V> value;

  private ChangeAwareMap<K, V> changeAwareMap;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param keyProperty the {@link #getKeyProperty() key property}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public MapProperty(String name, SimpleProperty<K> keyProperty, Property<V> valueProperty) {

    this(name, keyProperty, valueProperty, PropertyMetadataNone.getInstance());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param keyProperty the {@link #getKeyProperty() key property}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  @SuppressWarnings("unchecked")
  public MapProperty(String name, SimpleProperty<K> keyProperty, Property<V> valueProperty,
      PropertyMetadata<Map<K, V>> metadata) {

    super(name, valueProperty, metadata);
    if (keyProperty == null) {
      keyProperty = (SimpleProperty<K>) metadata.get(METADATA_KEY_KEY_PROPERTY);
    }
    this.keyProperty = keyProperty;
  }

  @Override
  public SimpleProperty<K> getKeyProperty() {

    return this.keyProperty;
  }

  @Override
  protected Map<K, V> doGet() {

    if (this.changeAwareMap != null) {
      return this.changeAwareMap;
    }
    return this.value;
  }

  @Override
  protected void doSet(Map<K, V> newValue) {

    if (this.changeAwareMap != null) {
      if (newValue == null) {
        this.changeAwareMap.clear();
      } else {
        this.changeAwareMap.putAll(newValue);
      }
    } else {
      this.value = newValue;
    }
  }

  @Override
  protected ValidationResult doValidate(Map<K, V> map, String source) {

    ValidationResult result = super.doValidate(map, source);
    if ((this.valueProperty != null) || (this.keyProperty != null)) {
      if ((map != null) && !map.isEmpty()) {
        ValidationResultBuilder builder = new ValidationResultBuilder();
        builder.add(result);
        for (Entry<K, V> entry : map.entrySet()) {
          if (this.valueProperty != null) {
            this.valueProperty.set(entry.getValue());
            builder.add(this.valueProperty.validate());
          }
          if (this.keyProperty != null) {
            this.keyProperty.set(entry.getKey());
            builder.add(this.keyProperty.validate());
          }
        }
        result = builder.build(source);
      }
    }
    return result;
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareMap != null);
  }

  @Override
  public ChangeAwareMap<K, V> getChangeAwareValue() {

    if (this.changeAwareMap == null) {
      this.changeAwareMap = ChangeAwareMaps.of(this.value);
      this.changeAwareMap.addListener(change -> {
        invalidateProperties();
        fireChange(change);
      });
    }
    return this.changeAwareMap;
  }

  @SuppressWarnings("unchecked")
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
    map = getOrCreate();
    do {
      K key;
      if (this.keyProperty == null) {
        key = (K) reader.readName();
      } else {
        key = this.keyProperty.parse(reader.readName());
      }
      this.valueProperty.read(reader);
      V mapValue = this.valueProperty.get();
      map.put(key, mapValue);
    } while (!reader.readEnd());
  }

  @Override
  public void write(StructuredWriter writer) {

    Map<K, V> map = getValue();
    if (map == null) {
      writer.writeValueAsNull();
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
