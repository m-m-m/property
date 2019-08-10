/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigdecimals;

import java.math.BigDecimal;
import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderBigDecimal;

/**
 * This is the implementation of {@link WritableBigDecimalProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class BigDecimalProperty extends NumberProperty<BigDecimal> implements WritableBigDecimalProperty {

  private BigDecimal value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public BigDecimalProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public BigDecimalProperty(String name, Object bean, AbstractValidator<? super BigDecimal> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public BigDecimalProperty(String name, Object bean, Supplier<BigDecimal> expression) {

    super(name, bean, expression);
  }

  @Override
  protected BigDecimal doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(BigDecimal newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderBigDecimal<PropertyBuilder<BigDecimalProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderBigDecimal<>(x));
  }

}
