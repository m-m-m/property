/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.util.lang.api.Builder;
import net.sf.mmm.util.validation.api.Validatable;
import net.sf.mmm.util.validation.api.ValidationFailure;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.ComposedValidationFailure;
import net.sf.mmm.util.validation.base.ObjectValidatorBuilder;
import net.sf.mmm.util.validation.base.ValidationFailureSuccess;
import net.sf.mmm.util.validation.base.ValidatorNone;
import net.sf.mmm.value.observable.AbstractWritableObservableValue;

/**
 * Implementation of {@link WritableProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class AbstractProperty<V> extends AbstractWritableObservableValue<V>
    implements WritableProperty<V>, Cloneable {

  private String name;

  private Object bean;

  private AbstractValidator<? super V> validator;

  private ValidationFailure validationResult;

  private boolean readOnly;

  private AbstractProperty<V> readOnlyProperty;

  private Supplier<? extends V> expression;

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public AbstractProperty(String name, Object bean) {

    this(name, bean, (AbstractValidator<? super V>) null);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public AbstractProperty(String name, Object bean, AbstractValidator<? super V> validator) {

    super();
    this.name = name;
    this.bean = bean;
    this.readOnly = false;
    this.expression = null;
    if (validator == null) {
      this.validator = ValidatorNone.getInstance();
    } else {
      this.validator = validator;
    }
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public AbstractProperty(String name, Object bean, Supplier<? extends V> expression) {

    super();
    this.name = name;
    this.bean = bean;
    this.readOnly = true;
    this.expression = expression;
    this.validator = null;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public Object getBean() {

    return this.bean;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected AbstractProperty<V> clone() {

    try {
      return (AbstractProperty<V>) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * @return a new empty instance of this property.
   */
  protected AbstractProperty<V> copy() {

    AbstractProperty<V> copy = clone();
    copy.bindInternal(null);
    copy.readOnlyProperty = null;
    copy.validationResult = null;
    return copy;
  }

  /**
   * Creates a new empty instance of this property with the given new parameters.
   *
   * @param newBean the new {@link #getBean() bean}.
   * @return the new property instance.
   */
  public final AbstractProperty<V> copy(Object newBean) {

    return copy(this.name, newBean, this.validator);
  }

  /**
   * Creates a new empty instance of this property with the given new parameters.
   *
   * @param newValidator the new {@link #getValidator() validator}.
   * @return the new property instance.
   */
  public final AbstractProperty<V> copy(AbstractValidator<? super V> newValidator) {

    return copy(this.name, this.bean, newValidator);
  }

  /**
   * Creates a new empty instance of this property with the given new parameters.
   *
   * @param newBean the new {@link #getBean() bean}.
   * @param newValidator the new {@link #getValidator() validator}.
   * @return the new property instance.
   */
  public final AbstractProperty<V> copy(Object newBean, AbstractValidator<? super V> newValidator) {

    return copy(this.name, newBean, newValidator);
  }

  /**
   * Creates a new empty instance of this property with the given new parameters.
   *
   * @param newName the new {@link #getName() name}.
   * @param newBean the new {@link #getBean() bean}.
   * @return the new property instance.
   */
  public final AbstractProperty<V> copy(String newName, Object newBean) {

    return copy(newName, newBean, this.validator);
  }

  /**
   * Creates a new empty instance of this property with the given new parameters.
   *
   * @param newName the new {@link #getName() name}.
   * @param newBean the new {@link #getBean() bean}.
   * @param newValidator the new {@link #getValidator() validator}.
   * @return the new property instance.
   */
  public final AbstractProperty<V> copy(String newName, Object newBean, AbstractValidator<? super V> newValidator) {

    AbstractProperty<V> copy = clone();
    copy.name = newName;
    copy.bean = newBean;
    copy.validator = newValidator;
    return copy;
  }

  /**
   * @return the {@link AbstractValidator}.
   */
  public final AbstractValidator<? super V> getValidator() {

    return this.validator;
  }

  @Override
  public final boolean isMandatory() {

    return this.validator.isMandatory();
  }

  /**
   * @param <PROPERTY> the generic type of the {@link AbstractProperty}.
   * @param <BUILDER> the generic type of the {@link ObjectValidatorBuilder} for {@literal <VALUE>}.
   * @param factory the {@link Function} to use as factory for the builder.
   * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
   *         {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
   *         property with the configured validator.
   */
  protected <PROPERTY extends AbstractProperty<? extends V>, BUILDER extends ObjectValidatorBuilder<? extends V, PropertyBuilder<PROPERTY>, ?>> BUILDER withValdidator(
      Function<PropertyBuilder<PROPERTY>, BUILDER> factory) {

    PropertyBuilder<PROPERTY> parentBuilder = new PropertyBuilder<>();
    BUILDER builder = factory.apply(parentBuilder);
    parentBuilder.builder = builder;
    return builder;
  }

  @Override
  public V getValue() {

    if (this.expression != null) {
      return this.expression.get();
    }
    return super.getValue();
  }

  @Override
  protected final void requireWritable() throws IllegalStateException {

    super.requireWritable();
    if (this.expression != null) {
      throw new IllegalStateException("Property " + getName() + " is computed and cannot be modified.");
    }
    if (isReadOnly()) {
      throw new IllegalStateException("Property " + getName() + " is readonly and cannot be modified.");
    }
  }

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
      AbstractProperty<V> copy = copy();
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

  /**
   * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
   *         {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
   *         property with the configured validator.
   */
  public abstract ObjectValidatorBuilder<? extends V, ? extends PropertyBuilder<? extends AbstractProperty<? extends V>>, ?> withValdidator();

  /**
   * @param sb the {@link StringBuilder} where to append the details of this property for
   *        {@link #toString()}-Representation.
   */
  @Override
  protected void toString(StringBuilder sb) {

    sb.append("name=");
    sb.append(this.name);
    sb.append(',');
    super.toString(sb);
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValue(getValue());
  }

  @Override
  public void read(StructuredReader reader) {

    V value = reader.readValue(getValueClass());
    setValue(value);
  }

  @Override
  public int hashCode() {

    return Objects.hash(getClass(), this.name);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    AbstractProperty<?> other = (AbstractProperty<?>) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(getValue(), other.getValue())) {
      return false;
    }
    // if (!Objects.equals(this.validator, other.validator)) {
    // return false;
    // }
    return true;
  }

  /**
   * Implementation of {@link Builder} to {@link #build() build} a copy of the {@link AbstractProperty property}.
   *
   * @param <T> the generic type of the {@link AbstractProperty property}.
   */
  public class PropertyBuilder<T extends AbstractProperty<? extends V>> implements Builder<T> {

    private ObjectValidatorBuilder<?, ? extends PropertyBuilder<? extends T>, ?> builder;

    private boolean assignValue;

    private Object newBean;

    /**
     * The constructor.
     */
    public PropertyBuilder() {

      super();
    }

    /**
     * @param otherBean the new value of {@link AbstractProperty#getBean()}.
     * @return this build instance for fluent API calls.
     */
    public PropertyBuilder<T> withBean(Object otherBean) {

      this.newBean = otherBean;
      return this;
    }

    /**
     * Also assign the {@link #getValue() value} from the original property. <br/>
     * <b>ATTENTION:</b><br/>
     * A {@link WritableProperty} may hold any kind of {@link #getValue() value} so the value may be mutable. If you are
     * using this builder to create a copy of the property this might have undesired effects.
     *
     * @return this build instance for fluent API calls.
     */
    public PropertyBuilder<T> withValue() {

      this.assignValue = true;
      return this;
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T build() {

      AbstractValidator<? super V> newValidator = (AbstractValidator) this.builder.build();
      newValidator = AbstractProperty.this.validator.append((AbstractValidator) newValidator);
      if ((AbstractProperty.this.validator == newValidator) && (this.newBean == null)) {
        return (T) AbstractProperty.this;
      }
      AbstractProperty<V> copy;
      if (this.newBean == null) {
        copy = copy(newValidator);
      } else {
        copy = copy(this.newBean, newValidator);
      }
      if (this.assignValue) {
        copy.setValue(getValue());
      }
      return (T) copy;
    }
  }

}
