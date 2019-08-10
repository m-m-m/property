/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.shorts;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderShort;

/**
 * This is the implementation of {@link WritableShortProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class ShortProperty extends NumberProperty<Short> implements WritableShortProperty {

  private Short value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public ShortProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public ShortProperty(String name, Object bean, AbstractValidator<? super Short> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public ShortProperty(String name, Object bean, Supplier<Short> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Short doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Short newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderShort<PropertyBuilder<ShortProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderShort<>(x));
  }

}
