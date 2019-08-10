/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api;

import java.util.function.Supplier;

import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the abstract base implementation of a regular {@link WritableProperty}. Here regular means that the
 * {@link #getValue() value} type is not special such as a {@link java.util.Collection}.
 *
 * @param <V> is the generic type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public abstract class AbstractRegularProperty<V> extends AbstractValueProperty<V> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public AbstractRegularProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public AbstractRegularProperty(String name, Object bean, AbstractValidator<? super V> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public AbstractRegularProperty(String name, Object bean, Supplier<? extends V> expression) {

    super(name, bean, expression);
  }

}
