/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers;

import java.lang.reflect.Type;
import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link WritableContainerProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public abstract class ContainerProperty<V, E> extends AbstractProperty<V> implements WritableContainerProperty<V, E> {

  private Class<E> componentClass;

  private Type componentType;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public ContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

    this(name, componentClass, componentType, bean, (AbstractValidator<? super V>) null);
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
  public ContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      AbstractValidator<? super V> validator) {

    super(name, bean, validator);
    this.componentClass = componentClass;
    this.componentType = componentType;
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
  public ContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<? extends V> expression) {

    super(name, bean, expression);
    this.componentClass = componentClass;
    this.componentType = componentType;
  }

  @Override
  public Class<E> getComponentClass() {

    return this.componentClass;
  }

  @Override
  public Type getComponentType() {

    return this.componentType;
  }

}
