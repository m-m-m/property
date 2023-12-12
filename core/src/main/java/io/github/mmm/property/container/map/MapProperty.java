/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.marshall.id.StructuredIdMapping;
import io.github.mmm.marshall.id.StructuredIdMappingObject;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.container.ContainerProperty;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.property.object.WritableSimpleProperty;
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
public class MapProperty<K, V> extends ContainerProperty<Map<K, V>, V>
    implements WritableMapProperty<K, V>, StructuredIdMappingObject {

  private static final String NAME_KEY = "key";

  private static final String NAME_VALUE = "value";

  /** @see #getKeyProperty() */
  protected final SimpleProperty<K> keyProperty;

  private Map<K, V> value;

  private ChangeAwareMap<K, V> changeAwareMap;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param keyProperty the {@link #getKeyProperty() key property}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public MapProperty(String name, WritableSimpleProperty<K> keyProperty, WritableProperty<V> valueProperty) {

    this(name, keyProperty, valueProperty, PropertyMetadataNone.get());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param keyProperty the {@link #getKeyProperty() key property}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MapProperty(String name, WritableSimpleProperty<K> keyProperty, WritableProperty<V> valueProperty,
      PropertyMetadata<Map<K, V>> metadata) {

    super(name, valueProperty, metadata);
    this.keyProperty = (SimpleProperty<K>) keyProperty;
  }

  @Override
  public WritableSimpleProperty<K> getKeyProperty() {

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
        ValidationResultBuilder builder = new ValidationResultBuilder(false);
        builder.add(result);
        int index = 0;
        for (Entry<K, V> entry : map.entrySet()) {
          String indexSource = "#" + index;
          if (this.valueProperty != null) {
            this.valueProperty.set(entry.getValue());
            builder.add(this.valueProperty.doValidate(indexSource));
          }
          if (this.keyProperty != null) {
            this.keyProperty.set(entry.getKey());
            builder.add(this.keyProperty.doValidate(indexSource));
          }
          index++;
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

  @Override
  protected void readValue(StructuredReader reader) {

    Map<K, V> map;
    boolean empty;
    if (reader.getFormat().isIdBased()) {
      empty = !reader.readStartArray();
    } else {
      empty = !reader.readStartObject(this);
    }
    if (empty) {
      map = getValue();
      if (map != null) {
        map.clear();
      }
    } else {
      map = getOrCreate();
      do {
        K mapKey = readMapKey(reader);
        V mapValue = readMapValue(reader);
        map.put(mapKey, mapValue);
      } while (!reader.readEnd());
    }
  }

  private K readMapKey(StructuredReader reader) {

    if (reader.getFormat().isIdBased()) {
      if (reader.isName(NAME_KEY)) {
        K key = readMapKey(reader, false);
        if (reader.isName(NAME_VALUE)) {
          return key;
        }
      }
      throw new IllegalStateException("Invalid map entry - unexpected property " + reader.getName());
    } else {
      return readMapKey(reader, true);
    }
  }

  /**
   * @param reader the {@link StructuredReader}.
   * @param asName - {@code true} to read the key as {@link StructuredReader#readName() name}, {@code false} otherwise
   *        (as {@link StructuredReader#readValue() value}).
   * @return the unmarshalled key.
   */
  @SuppressWarnings("unchecked")
  protected K readMapKey(StructuredReader reader, boolean asName) {

    if (this.keyProperty == null) {
      if (asName) {
        return (K) reader.readName(); // will fail if K != String
      }
      return (K) reader.readValue();
    } else {
      this.keyProperty.read(reader);
      return this.keyProperty.get();
    }
  }

  /**
   * @param reader the {@link StructuredReader} to read.
   * @return the unmarshalled value.
   */
  @SuppressWarnings("unchecked")
  protected V readMapValue(StructuredReader reader) {

    if (this.valueProperty == null) {
      return (V) reader.readValue(); // may fail
    } else {
      this.valueProperty.read(reader);
      return this.valueProperty.get();
    }
  }

  @Override
  public void write(StructuredWriter writer) {

    Map<K, V> map = getValue();
    if (map == null) {
      writer.writeValueAsNull();
      return;
    }
    if (writer.getFormat().isIdBased()) {
      writer.writeStartArray();
      for (Entry<K, V> entry : map.entrySet()) {
        writer.writeName(NAME_KEY);
        writeMapKey(writer, entry.getKey(), false);
        writer.writeName(NAME_VALUE);
        writeMapValue(writer, entry.getValue());
      }
    } else {
      writer.writeStartObject(this);
      for (Entry<K, V> entry : map.entrySet()) {
        writeMapKey(writer, entry.getKey(), true);
        writeMapValue(writer, entry.getValue());
      }
    }
    writer.writeEnd();
  }

  /**
   * Implementation of {@link #write(StructuredWriter)} for a {@link Map#containsKey(Object) map key}.
   *
   * @param writer the {@link StructuredWriter}.
   * @param key the {@link Map#containsKey(Object) map key} to marshall.
   * @param asName - {@code true} to write the key as {@link StructuredWriter#writeName(String) name}, {@code false}
   *        otherwise (as {@link StructuredWriter#writeValue(Object) value}).
   */
  protected void writeMapKey(StructuredWriter writer, K key, boolean asName) {

    String string;
    if (this.keyProperty == null) {
      string = Objects.toString(key);
    } else {
      this.keyProperty.set(key);
      string = this.keyProperty.getAsString();
    }
    if (asName) {
      writer.writeName(string);
    } else {
      writer.writeValueAsString(string);
    }
  }

  /**
   * Implementation of {@link #write(StructuredWriter)} for a {@link Map#containsValue(Object) map value}.
   *
   * @param writer the {@link StructuredWriter}.
   * @param mapValue the {@link Map#containsValue(Object) map value} to marshall.
   */
  protected void writeMapValue(StructuredWriter writer, V mapValue) {

    if (this.valueProperty == null) {
      writer.writeValue(mapValue);
    } else {
      this.valueProperty.set(mapValue);
      this.valueProperty.write(writer);
    }
  }

  @Override
  public StructuredIdMapping defineIdMapping() {

    return StructuredIdMapping.of(NAME_KEY, NAME_VALUE);
  }

}
