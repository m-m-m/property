/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.collection;

import java.util.Collection;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.container.ContainerProperty;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultBuilder;

/**
 * Implementation of {@link WritableCollectionProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of the {@link Collection#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public abstract class CollectionProperty<V extends Collection<E>, E> extends ContainerProperty<V, E>
    implements WritableCollectionProperty<V, E> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public CollectionProperty(String name, WritableProperty<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public CollectionProperty(String name, WritableProperty<E> valueProperty, PropertyMetadata<V> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected ValidationResult doValidate(V collection, String source) {

    ValidationResult result = super.doValidate(collection, source);
    if (this.valueProperty != null) {
      if ((collection != null) && !collection.isEmpty()) {
        ValidationResultBuilder builder = new ValidationResultBuilder(false);
        builder.add(result);
        int index = 0;
        for (E element : collection) {
          this.valueProperty.set(element);
          builder.add(this.valueProperty.doValidate("#" + index));
          index++;
        }
        result = builder.build(source);
      }
    }
    return result;
  }

  @SuppressWarnings({ "unchecked", "null" })
  @Override
  protected V readValue(StructuredReader reader, boolean apply) {

    V collection = null;
    if (!apply) {
      collection = create();
    }
    if (!reader.readStartArray()) {
      if (apply) {
        collection = getValue();
        if (collection != null) {
          collection.clear();
        }
      }
    } else {
      if (apply) {
        collection = getOrCreate();
      }
      do {
        E element;
        if (this.valueProperty == null) {
          element = (E) reader.readValue();
        } else {
          this.valueProperty.read(reader);
          element = this.valueProperty.get();
        }
        collection.add(element);
      } while (!reader.readEnd());
    }
    return collection;
  }

  @Override
  public void writeValue(StructuredWriter writer, V collection) {

    if (collection == null) {
      writer.writeValueAsNull();
      return;
    }
    writer.writeStartArray();
    for (E element : collection) {
      if (this.valueProperty == null) {
        writer.writeValue(element);
      } else {
        this.valueProperty.set(element);
        this.valueProperty.write(writer);
      }
    }
    writer.writeEnd();
  }

}
