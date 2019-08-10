/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.localdatetimes;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.time.ValidatorBuilderLocalDateTime;

/**
 * This is the implementation of {@link WritableLocalDateTimeProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LocalDateTimeProperty extends AbstractRegularProperty<LocalDateTime>
    implements WritableLocalDateTimeProperty {

  private LocalDateTime value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public LocalDateTimeProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public LocalDateTimeProperty(String name, Object bean, AbstractValidator<? super LocalDateTime> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public LocalDateTimeProperty(String name, Object bean, Supplier<LocalDateTime> expression) {

    super(name, bean, expression);
  }

  @Override
  protected LocalDateTime doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(LocalDateTime newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderLocalDateTime<PropertyBuilder<LocalDateTimeProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderLocalDateTime<>(x));
  }

}
