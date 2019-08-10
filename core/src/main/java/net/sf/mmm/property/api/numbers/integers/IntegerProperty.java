/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.integers;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderInteger;

/**
 * Implementation of {@link WritableIntegerProperty}.
 *
 * @since 1.0.0
 */
public class IntegerProperty extends NumberProperty<Integer> implements WritableIntegerProperty {

  private Integer value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public IntegerProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public IntegerProperty(String name, Object bean, AbstractValidator<? super Integer> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public IntegerProperty(String name, Object bean, Supplier<Integer> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Integer doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Integer newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderInteger<PropertyBuilder<IntegerProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderInteger<>(x));
  }

}
