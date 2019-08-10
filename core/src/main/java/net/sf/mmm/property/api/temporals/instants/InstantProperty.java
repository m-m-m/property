/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.temporals.instants;

import java.time.Instant;
import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.time.ValidatorBuilderInstant;

/**
 * This is the implementation of {@link WritableInstantProperty}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class InstantProperty extends AbstractRegularProperty<Instant> implements WritableInstantProperty {

  private Instant value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public InstantProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public InstantProperty(String name, Object bean, AbstractValidator<? super Instant> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public InstantProperty(String name, Object bean, Supplier<Instant> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Instant doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Instant newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderInstant<PropertyBuilder<InstantProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderInstant<>(x));
  }

}
