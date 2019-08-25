/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.collections;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.Supplier;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.property.api.containers.ContainerProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;

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

    this(name, componentClass, componentType, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public CollectionProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

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
  public CollectionProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      AbstractValidator<? super V> validator) {

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
  public CollectionProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<V> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  public void read(StructuredReader reader) {

    Collection<E> list;
    if (!reader.readStartArray()) {
      list = getValue();
      if (list != null) {
        list.clear();
      }
      return;
    }
    list = getOrCreateValue();
    Class<E> componentClass = getComponentClass();
    do {
      E element = reader.readValue(componentClass);
      list.add(element);
    } while (!reader.readEnd());
  }

  @Override
  public void write(StructuredWriter writer) {

    Collection<E> list = getValue();
    if ((list != null) && !list.isEmpty()) {
      writer.writeStartArray();
      for (E element : list) {
        writer.writeValue(element);
      }
      writer.writeEnd();
    }
  }

}
