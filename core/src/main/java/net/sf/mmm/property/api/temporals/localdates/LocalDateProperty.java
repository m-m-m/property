/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.localdates;

import java.time.LocalDate;
import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.time.ValidatorBuilderLocalDate;

/**
 * This is the implementation of {@link WritableLocalDateProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LocalDateProperty extends AbstractRegularProperty<LocalDate> implements WritableLocalDateProperty {

  private LocalDate value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public LocalDateProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public LocalDateProperty(String name, Object bean, AbstractValidator<? super LocalDate> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public LocalDateProperty(String name, Object bean, Supplier<LocalDate> expression) {

    super(name, bean, expression);
  }

  @Override
  protected LocalDate doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(LocalDate newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderLocalDate<PropertyBuilder<LocalDateProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderLocalDate<>(x));
  }

}
