/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.numbers.bytes;

import java.util.function.Supplier;

import net.sf.mmm.property.api.numbers.NumberProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.number.ValidatorBuilderByte;

/**
 * Implementation of {@link WritableByteProperty}.
 *
 * @since 1.0.0
 */
public class ByteProperty extends NumberProperty<Byte> implements WritableByteProperty {

  private Byte value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public ByteProperty(String name, Object bean) {

    super(name, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public ByteProperty(String name, Object bean, AbstractValidator<? super Byte> validator) {

    super(name, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public ByteProperty(String name, Object bean, Supplier<Byte> expression) {

    super(name, bean, expression);
  }

  @Override
  protected Byte doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Byte newValue) {

    this.value = newValue;
  }

  @Override
  public ValidatorBuilderByte<PropertyBuilder<ByteProperty>> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderByte<>(x));
  }

}
