/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers;

import java.lang.reflect.Type;
import java.util.function.Supplier;

import net.sf.mmm.property.api.booleans.BooleanProperty;
import net.sf.mmm.property.api.booleans.ReadableBooleanProperty;
import net.sf.mmm.property.api.numbers.integers.IntegerProperty;
import net.sf.mmm.property.api.numbers.integers.ReadableIntegerProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link WritableChangeAwareContainerProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public abstract class ChangeAwareContainerProperty<V, E> extends ContainerProperty<V, E>
    implements WritableChangeAwareContainerProperty<V, E> {

  private IntegerProperty sizeProperty;

  private BooleanProperty emptyProperty;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public ChangeAwareContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

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
  public ChangeAwareContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean,
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
  public ChangeAwareContainerProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<? extends V> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  public ReadableIntegerProperty sizeProperty() {

    if (this.sizeProperty == null) {
      this.sizeProperty = new IntegerProperty(getName() + ".size", getBean(), () -> Integer.valueOf(size()));
    }
    return this.sizeProperty;
  }

  @Override
  public ReadableBooleanProperty emptyProperty() {

    if (this.emptyProperty == null) {
      this.emptyProperty = new BooleanProperty(getName() + ".empty", getBean(), () -> Boolean.valueOf(isEmpty()));
    }
    return this.emptyProperty;
  }

  /**
   * Invalidates internal properties such as {@link #sizeProperty()} and {@link #emptyProperty()}.
   */
  protected void invalidateProperties() {

    fireEventFor(this.sizeProperty);
    fireEventFor(this.emptyProperty);
  }

}
