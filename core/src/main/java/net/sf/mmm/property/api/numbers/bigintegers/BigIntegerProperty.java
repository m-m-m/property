/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bigintegers;

import java.math.BigInteger;
import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderBigInteger;

/**
 * This is the implementation of {@link WritableBigIntegerProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class BigIntegerProperty extends NumberProperty<BigInteger> implements WritableBigIntegerProperty {

  private BigInteger value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public BigIntegerProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public BigIntegerProperty(String name, Object bean, AbstractValidator<? super BigInteger> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public BigIntegerProperty(String name, Object bean, Supplier<BigInteger> expression) {

    super(name, bean, expression);
  }

  @Override
  protected BigInteger doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(BigInteger newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderBigInteger<PropertyBuilder<BigIntegerProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderBigInteger<>(x));
  }

}
