/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.objects;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Supplier;

import net.sf.mmm.property.api.AbstractRegularProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.ObjectValidatorBuilder;
import net.sf.mmm.util.validation.base.ValidatorBuilderObject;

/**
 * Generic implementation of {@link WritableProperty} for arbitrary objects that do not have their own custom
 * implementation.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class ObjectProperty<V> extends AbstractRegularProperty<V> implements WritableObjectProperty<V> {

  private final Class<V> valueClass;

  private final Type valueType;

  private V value;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   */
  public ObjectProperty(String name, Class<V> valueClass) {

    this(name, valueClass, valueClass, null, (AbstractValidator<? super V>) null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   * @param bean - see {@link #getBean()}.
   */
  public ObjectProperty(String name, Class<V> valueClass, Object bean) {

    this(name, valueClass, valueClass, bean, (AbstractValidator<? super V>) null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public ObjectProperty(String name, Class<V> valueClass, Object bean, Supplier<? extends V> expression) {

    this(name, valueClass, valueClass, bean, expression);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #getValidator()}.
   */
  public ObjectProperty(String name, Class<V> valueClass, Object bean, AbstractValidator<? super V> validator) {

    this(name, valueClass, valueClass, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   * @param valueType - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #getValidator()}.
   */
  public ObjectProperty(String name, Class<V> valueClass, Type valueType, Object bean,
      AbstractValidator<? super V> validator) {

    super(name, bean, validator);
    Objects.requireNonNull(valueClass);
    Objects.requireNonNull(valueType);
    this.valueClass = valueClass;
    this.valueType = valueType;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param valueClass - see {@link #getValueClass()}.
   * @param valueType - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public ObjectProperty(String name, Class<V> valueClass, Type valueType, Object bean,
      Supplier<? extends V> expression) {

    super(name, bean, expression);
    Objects.requireNonNull(valueClass);
    Objects.requireNonNull(valueType);
    this.valueClass = valueClass;
    this.valueType = valueType;
  }

  @Override
  public Class<V> getValueClass() {

    return this.valueClass;
  }

  @Override
  public Type getValueType() {

    return this.valueType;
  }

  @Override
  protected V doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(V newValue) {

    assert (this.valueClass.isInstance(newValue));
    this.value = newValue;
  }

  /**
   * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
   *         {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
   *         property with the configured validator.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public ObjectValidatorBuilder<? extends V, ? extends PropertyBuilder<? extends ObjectProperty<? extends V>>, ?> withValdidator() {

    return withValdidator(x -> new ValidatorBuilderObject(x));
  }

}
