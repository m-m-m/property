/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.sets;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import net.sf.mmm.property.api.containers.ContainerProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.collection.AbstractCollectionValidatorBuilder;
import net.sf.mmm.util.validation.base.collection.ValidatorBuilderCollection;

/**
 * Implementation of {@link WritableSetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class SetProperty<E> extends ContainerProperty<Set<E>, E> implements WritableSetProperty<E> {

  private Set<E> value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public SetProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

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
  public SetProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      AbstractValidator<? super Set<E>> validator) {

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
  public SetProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<Set<E>> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  protected Set<E> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Set<E> newValue) {

    this.value = newValue;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public AbstractCollectionValidatorBuilder<E, Set<E>, PropertyBuilder<SetProperty<E>>, ?> withValdidator() {

    Function factory = new Function<PropertyBuilder<SetProperty<E>>, ValidatorBuilderCollection<E, PropertyBuilder<SetProperty<E>>>>() {

      @Override
      public ValidatorBuilderCollection<E, PropertyBuilder<SetProperty<E>>> apply(PropertyBuilder<SetProperty<E>> t) {

        return new ValidatorBuilderCollection<>(t);
      }
    };
    return (ValidatorBuilderCollection) withValdidator(factory);
  }

}
