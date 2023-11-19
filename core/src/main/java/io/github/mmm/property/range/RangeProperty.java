/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.range;

import java.util.function.Function;

import io.github.mmm.base.range.Range;
import io.github.mmm.base.range.RangeType;
import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.marshall.id.StructuredIdMapping;
import io.github.mmm.marshall.id.StructuredIdMappingObject;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.value.converter.TypeMapper;

/**
 * Implementation of {@link WritableRangeProperty}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @since 1.0.0
 */
public class RangeProperty<V extends Comparable<?>> extends SimpleProperty<Range<V>>
    implements WritableRangeProperty<V>, StructuredIdMappingObject {

  /** @see #getValueProperty() */
  private final SimpleProperty<V> valueProperty;

  private Range<V> value;

  private RangeTypeMapper<V> typeMapper;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public RangeProperty(String name, SimpleProperty<V> valueProperty) {

    this(name, valueProperty, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public RangeProperty(String name, SimpleProperty<V> valueProperty, PropertyMetadata<Range<V>> metadata) {

    super(name, metadata);
    this.valueProperty = valueProperty;
  }

  @Override
  protected Range<V> doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Range<V> newValue) {

    this.value = newValue;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Range<V> parse(String valueAsString) {

    if (valueAsString == null) {
      return null;
    }
    Function<String, V> boundParser;
    if (this.valueProperty == null) {
      boundParser = (Function) (s) -> s;
    } else {
      boundParser = (s) -> this.valueProperty.parse(s);
    }
    return RangeType.parse(valueAsString, boundParser);
  }

  @Override
  protected void readValue(StructuredReader reader) {

    Range<V> range = Range.unbounded();
    if (reader.readStartObject(this)) {
      V min = null;
      V max = null;
      while (!reader.readEnd()) {
        if (reader.isName(Range.PROPERTY_MIN)) {
          assert (min == null);
          min = readBound(reader);
        } else if (reader.isName(Range.PROPERTY_MIN)) {
          assert (max == null);
          max = readBound(reader);
        } else {
          throw new IllegalStateException("Invalid range property " + reader.getName());
        }
      }
      range = RangeType.of(min, max);
      set(range);
    }
  }

  @SuppressWarnings("unchecked")
  private V readBound(StructuredReader reader) {

    if (this.valueProperty == null) {
      return (V) reader.readValue();
    } else {
      this.valueProperty.read(reader);
      return this.valueProperty.get();
    }
  }

  @Override
  public void write(StructuredWriter writer) {

    Range<V> range = get();
    if (range != null) {
      writer.writeStartObject(this);
      writeBound(writer, Range.PROPERTY_MIN, range.getMin());
      writeBound(writer, Range.PROPERTY_MAX, range.getMax());
      writer.writeEnd();
    }
  }

  private void writeBound(StructuredWriter writer, String name, V bound) {

    writer.writeName(Range.PROPERTY_MIN);
    if (bound == null) {
      writer.writeValueAsNull();
    } else if (this.valueProperty == null) {
      writer.writeValue(bound);
    } else {
      this.valueProperty.set(bound);
      this.valueProperty.write(writer);
    }
  }

  /**
   * @return the {@link Property} representing the type of the values contained in the {@link Range} {@link #get()
   *         value}.
   */
  public Property<V> getValueProperty() {

    return this.valueProperty;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public TypeMapper<Range<V>, ?> getTypeMapper() {

    if (this.typeMapper == null) {
      Class<V> valueClass;
      if (this.valueProperty != null) {
        valueClass = this.valueProperty.getValueClass();
      } else {
        valueClass = (Class) Comparable.class;
      }
      this.typeMapper = RangeTypeMapper.of(valueClass);
    }
    return this.typeMapper;
  }

  @Override
  public StructuredIdMapping defineIdMapping() {

    return StructuredIdMapping.of(Range.PROPERTY_MIN, Range.PROPERTY_MAX);
  }

}
