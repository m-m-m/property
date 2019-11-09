/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.collection;

import java.lang.reflect.Type;
import java.util.Collection;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.container.ContainerProperty;

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
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public CollectionProperty(String name, Class<E> componentClass, Type componentType) {

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
  public CollectionProperty(String name, Class<E> componentClass, Type componentType, PropertyMetadata<V> metadata) {

    super(name, componentClass, componentType, metadata);
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
      collection = getOrCreateValue();
    }
    do {
      E element = readElement(reader);
      collection.add(element);
    } while (!reader.readEnd());
  }

  /**
   * Implementation of {@link #read(StructuredReader)} for a single {@link Collection} element.
   *
   * @param reader the {@link StructuredReader} pointing to a value inside an array.
   * @return the unmarshalled element.
   */
  protected E readElement(StructuredReader reader) {

    return reader.readValue(getComponentClass());
  }

  @Override
  public void write(StructuredWriter writer) {

    Collection<E> list = getValue();
    if ((list != null) && !list.isEmpty()) {
      writer.writeStartArray();
      for (E element : list) {
        writeElement(writer, element);
      }
      writer.writeEnd();
    }
  }

  /**
   * Implementation of {@link #write(StructuredWriter)} for a single {@link Collection} element.
   *
   * @param writer the {@link StructuredWriter}.
   * @param element the {@link Collection} element to marshall.
   */
  protected void writeElement(StructuredWriter writer, E element) {

    writer.writeValue(element);
  }

}
