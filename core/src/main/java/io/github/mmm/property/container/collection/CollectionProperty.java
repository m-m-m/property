/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.collection;

import java.util.Collection;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
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
  public CollectionProperty(String name, Property<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public CollectionProperty(String name, Property<E> valueProperty, PropertyMetadata<V> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected ValidationResult doValidate(V collection, String source) {

    ValidationResult result = super.doValidate(collection, source);
    if (this.valueProperty != null) {
      if ((collection != null) && !collection.isEmpty()) {
        ValidationResultBuilder builder = new ValidationResultBuilder();
        builder.add(result);
        for (E element : collection) {
          this.valueProperty.set(element);
          builder.add(this.valueProperty.validate());
        }
        result = builder.build(source);
      }
    }
    return result;
  }

  @Override
  public void read(StructuredReader reader) {

    Collection<E> collection;
    if (!reader.readStartArray()) {
      collection = getValue();
      if (collection != null) {
        collection.clear();
      }
      return;
    }
    if (isChangeAware()) {
      collection = getChangeAwareValue();
    } else {
      collection = getOrCreate();
    }
    do {
      this.valueProperty.read(reader);
      E element = this.valueProperty.get();
      collection.add(element);
    } while (!reader.readEnd());
  }

  @Override
  public void write(StructuredWriter writer) {

    Collection<E> collection = get();
    if ((collection != null) && !collection.isEmpty()) {
      writer.writeStartArray();
      for (E element : collection) {
        this.valueProperty.set(element);
        this.valueProperty.write(writer);
      }
      writer.writeEnd();
    }
  }

}
