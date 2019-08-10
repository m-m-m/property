/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api;

import java.util.Objects;
import java.util.function.Supplier;

import net.sf.mmm.util.validation.api.Validatable;
import net.sf.mmm.util.validation.api.ValidationFailure;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.ComposedValidationFailure;
import net.sf.mmm.util.validation.base.ValidationFailureSuccess;
import net.sf.mmm.value.observable.BidirectionalBinding;
import net.sf.mmm.value.observable.BindingListener;
import net.sf.mmm.value.observable.ObservableEventListener;
import net.sf.mmm.value.observable.ObservableValue;

/**
 * This is the implementation of {@link WritableProperty}.
 *
 * @param <V> is the generic type of the {@link #getValue() value}.
 * @author hohwille
 * @since 1.0.0
 */
public abstract class AbstractValueProperty<V> extends AbstractProperty<V> {

  private ValidationFailure validationResult;

  private boolean readOnly;

  private AbstractValueProperty<V> readOnlyProperty;

  private ObservableValue<? extends V> binding;

  private BindingListener bindingListener;

  private Supplier<? extends V> expression;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public AbstractValueProperty(String name, Object bean) {

    this(name, bean, (AbstractValidator<? super V>) null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public AbstractValueProperty(String name, Object bean, AbstractValidator<? super V> validator) {

    super(name, bean, validator);
    this.readOnly = false;
    this.expression = null;
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public AbstractValueProperty(String name, Object bean, Supplier<? extends V> expression) {

    super(name, bean);
    this.readOnly = true;
    this.expression = expression;
  }

  @Override
  protected AbstractValueProperty<V> copy() {

    AbstractValueProperty<V> copy = (AbstractValueProperty<V>) super.copy();
    copy.binding = null;
    copy.readOnlyProperty = null;
    copy.validationResult = null;
    return copy;
  }

  @Override
  public final V getValue() {

    if (this.expression != null) {
      return this.expression.get();
    }
    if (this.binding != null) {
      return this.binding.getValue();
    }
    return doGetValue();
  }

  /**
   * @return the internal {@link #getValue() value}.
   */
  protected abstract V doGetValue();

  @Override
  public void bind(ObservableValue<? extends V> observable) {

    Objects.requireNonNull(observable, "observable");
    if (!observable.equals(this.binding)) {
      requireWritable();
      unbind();
      bindInternal(observable);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  void bindInternal(ObservableValue<? extends V> observable) {

    this.binding = observable;
    if (this.bindingListener == null) {
      this.bindingListener = new BindingListener(this);
    }
    observable.addListener((ObservableEventListener) this.bindingListener);
    fireEvent();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void unbind() {

    if (this.binding != null) {
      requireWritable();
      assignValueFrom(this.binding);
      this.binding.removeListener((ObservableEventListener) this.bindingListener);
      this.binding = null;
      this.bindingListener = null;
    }
  }

  @Override
  public boolean isBound() {

    return (this.binding != null);
  }

  @Override
  public void bindBidirectional(WritableProperty<V> other) {

    BidirectionalBinding.bind(this, other);
  }

  @Override
  public void unbindBidirectional(WritableProperty<V> other) {

    BidirectionalBinding.unbind(this, other);
  }

  @Override
  public void setValue(V value) {

    if (isBound()) {
      throw new IllegalStateException(
          getClass().getSimpleName() + "." + getName() + " : " + "A bound value cannot be set.");
    }
    requireWritable();
    V oldValue = doGetValue();
    if (!Objects.equals(oldValue, value)) {
      doSetValue(value);
      fireEventOldValue(oldValue);
    }
  }

  /**
   * @throws IllegalStateException if this property is {@link #isReadOnly() read-only}.
   */
  protected final void requireWritable() throws IllegalStateException {

    if (isReadOnly()) {
      throw new IllegalStateException("Property " + getName() + " is readonly.");
    }
  }

  /**
   * @param newValue the new {@link #getValue() value} to set.
   * @see #setValue(Object)
   */
  protected abstract void doSetValue(V newValue);

  @Override
  public boolean isValid() {

    if (this.validationResult == null) {
      validate();
    }
    return (this.validationResult == ValidationFailureSuccess.INSTANCE);
  }

  /**
   * Clears the cached internal {@link #validate() validation} result. Has to be called if the {@link #getValue() value}
   * has changed (from an external call).
   */
  protected void clearValidationResult() {

    this.validationResult = null;
  }

  @Override
  public ValidationFailure validate() {

    if (this.validationResult == null) {
      V v = getValue();
      String source = getName();
      ValidationFailure failure = getValidator().validate(v, source);
      if (v instanceof Validatable) {
        ValidationFailure failure2 = ((Validatable) v).validate();
        if (failure == null) {
          failure = failure2;
        } else if (failure2 != null) {
          failure = new ComposedValidationFailure(source, new ValidationFailure[] { failure, failure2 });
        }
      }
      if (failure == null) {
        failure = ValidationFailureSuccess.INSTANCE;
      }
      this.validationResult = failure;
    }
    if (this.validationResult == ValidationFailureSuccess.INSTANCE) {
      return null;
    }
    return this.validationResult;
  }

  @Override
  public WritableProperty<V> getReadOnly() {

    if (isReadOnly()) {
      return this;
    }
    if (this.readOnlyProperty == null) {
      AbstractValueProperty<V> copy = copy();
      copy.binding = null;
      copy.bindInternal(this);
      copy.readOnly = true;
      this.readOnlyProperty = copy;
    }
    return this.readOnlyProperty;
  }

  @Override
  public final boolean isReadOnly() {

    return this.readOnly;
  }

  @Override
  public int hashCode() {

    return Objects.hash(getClass(), getName());
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || !super.equals(obj)) {
      return false;
    }
    AbstractValueProperty<?> other = (AbstractValueProperty<?>) obj;
    if (!Objects.equals(this.binding, other.binding)) {
      return false;
    }
    return true;
  }

}
