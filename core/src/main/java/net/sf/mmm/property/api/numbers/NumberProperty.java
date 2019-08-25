/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers;

import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link WritableNumberProperty}.
 *
 * @param <N> type of the numeric {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class NumberProperty<N extends Number & Comparable<? super N>> extends AbstractRegularProperty<N>
    implements WritableNumberProperty<N> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public NumberProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public NumberProperty(String name, Object bean, AbstractValidator<? super N> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public NumberProperty(String name, Object bean, Supplier<? extends N> expression) {

    super(name, bean, expression);
  }

}
