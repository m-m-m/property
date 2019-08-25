/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.lists;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.sf.mmm.property.api.containers.collections.CollectionProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.collection.AbstractCollectionValidatorBuilder;
import net.sf.mmm.util.validation.base.collection.ValidatorBuilderCollection;

/**
 * Implementation of {@link WritableListProperty}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class ListProperty<E> extends CollectionProperty<List<E>, E> implements WritableListProperty<E> {

  private List<E> value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public ListProperty(String name, Class<E> componentClass, Type componentType) {

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
  public ListProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

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
  public ListProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      AbstractValidator<? super List<E>> validator) {

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
  public ListProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<List<E>> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  protected List<E> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(List<E> newValue) {

    this.value = newValue;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public AbstractCollectionValidatorBuilder<E, List<E>, PropertyBuilder<ListProperty<E>>, ?> withValdidator() {

    Function factory = new Function<PropertyBuilder<ListProperty<E>>, ValidatorBuilderCollection<E, PropertyBuilder<ListProperty<E>>>>() {

      @Override
      public ValidatorBuilderCollection<E, PropertyBuilder<ListProperty<E>>> apply(PropertyBuilder<ListProperty<E>> t) {

        return new ValidatorBuilderCollection<>(t);
      }
    };
    return (ValidatorBuilderCollection) withValdidator(factory);
  }

}
