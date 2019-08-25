/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.floats;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderFloat;

/**
 * Implementation of {@link WritableFloatProperty}.
 *
 * @since 1.0.0
 */
public class FloatProperty extends NumberProperty<Float> implements WritableFloatProperty {

  private Float value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public FloatProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public FloatProperty(String name, Object bean, AbstractValidator<? super Float> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public FloatProperty(String name, Object bean, Supplier<Float> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Float doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Float newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderFloat<PropertyBuilder<FloatProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderFloat<>(x));
  }

}
