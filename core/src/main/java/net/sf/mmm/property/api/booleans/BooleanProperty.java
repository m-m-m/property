/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.booleans;

import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.ValidatorBuilderBoolean;

/**
 * Implementation of {@link BooleanProperty}.
 *
 * @since 1.0.0
 */
public class BooleanProperty extends AbstractRegularProperty<Boolean> implements WritableBooleanProperty {

  private Boolean value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public BooleanProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public BooleanProperty(String name, Object bean, AbstractValidator<? super Boolean> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public BooleanProperty(String name, Object bean, Supplier<Boolean> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Boolean doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Boolean newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderBoolean<PropertyBuilder<BooleanProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderBoolean<>(x));
  }

}
