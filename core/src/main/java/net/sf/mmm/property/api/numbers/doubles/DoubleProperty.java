/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.doubles;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderDouble;

/**
 * Implementation of {@link WritableDoubleProperty}.
 *
 * @since 1.0.0
 */
public class DoubleProperty extends NumberProperty<Double> implements WritableDoubleProperty {

  private Double value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public DoubleProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public DoubleProperty(String name, Object bean, AbstractValidator<? super Double> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public DoubleProperty(String name, Object bean, Supplier<Double> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Double doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Double newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderDouble<PropertyBuilder<DoubleProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderDouble<>(x));
  }

}
