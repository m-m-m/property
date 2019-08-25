/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.longs;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderLong;

/**
 * Implementation of {@link WritableLongProperty}.
 *
 * @since 1.0.0
 */
public class LongProperty extends NumberProperty<Long> implements WritableLongProperty {

  private Long value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public LongProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public LongProperty(String name, Object bean, AbstractValidator<? super Long> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public LongProperty(String name, Object bean, Supplier<Long> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Long doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Long newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderLong<PropertyBuilder<LongProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderLong<>(x));
  }

}
