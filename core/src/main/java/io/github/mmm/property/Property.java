/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.validation.ComposedValidationFailure;
import io.github.mmm.validation.Validatable;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.value.observable.AbstractWritableObservableValue;

/**
 * Implementation of {@link WritableProperty}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public abstract class Property<V> extends AbstractWritableObservableValue<V> implements WritableProperty<V>, Cloneable {

  private String name;

  private PropertyMetadata<V> metadata;

  private ValidationResult validationResult;

  private Property<V> readOnlyProperty;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public Property(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public Property(String name, PropertyMetadata<V> metadata) {

    super();
    this.name = name;
    if (metadata == null) {
      this.metadata = PropertyMetadataNone.getInstance();
    } else {
      this.metadata = metadata;
    }
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public PropertyMetadata<V> getMetadata() {

    return this.metadata;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected final Property<V> clone() {

    try {
      return (Property<V>) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public final Property<V> copy(String newName, PropertyMetadata<V> newMetadata) {

    Property<V> copy = clone();
    copy.bindInternal(null);
    copy.readOnlyProperty = null;
    copy.validationResult = null;
    if (newName != null) {
      copy.name = newName;
    }
    if (newMetadata != null) {
      copy.metadata = newMetadata;
    }
    return copy;
  }

  @Override
  public V get() {

    Supplier<? extends V> expression = this.metadata.getExpression();
    if (expression != null) {
      return expression.get();
    }
    return super.get();
  }

  @Override
  protected final void requireWritable() throws IllegalStateException {

    super.requireWritable();
    if (isReadOnly()) {
      throw new IllegalStateException("Property " + getName() + " is readonly and cannot be modified.");
    }
  }

  @Override
  public boolean isValid() {

    if (this.validationResult == null) {
      validate();
    }
    return (this.validationResult.isValid());
  }

  /**
   * Clears the cached internal {@link #validate() validation} result. Has to be called if the {@link #get() value} has
   * changed (from an external call).
   */
  protected void clearValidationResult() {

    this.validationResult = null;
  }

  @Override
  public ValidationResult validate() {

    if (this.validationResult == null) {
      V v = get();
      String source = getName();
      ValidationResult result = this.metadata.getValidator().validate(v, source);
      if (v instanceof Validatable) {
        ValidationResult result2 = ((Validatable) v).validate();
        if (result.isValid()) {
          result = result2;
        } else if (!result2.isValid()) {
          result = new ComposedValidationFailure(source, new ValidationResult[] { result, result2 });
        }
      }
      this.validationResult = result;
    }
    return this.validationResult;
  }

  @Override
  public WritableProperty<V> getReadOnly() {

    if (this.readOnlyProperty == null) {
      Property<V> copy = copy(null, null);
      copy.bindInternal(this);
      copy.readOnlyProperty = copy;
      this.readOnlyProperty = copy;
    }
    return this.readOnlyProperty;
  }

  @Override
  public final boolean isReadOnly() {

    if (this.readOnlyProperty == this) {
      return true;
    } else if (this.metadata.getExpression() != null) {
      return true;
    }
    return false;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append("name=");
    sb.append(this.name);
    sb.append(',');
    super.toString(sb);
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValue(get());
  }

  @Override
  public void read(StructuredReader reader) {

    V value = reader.readValue(getValueClass());
    set(value);
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
    Property<?> other = (Property<?>) obj;
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(get(), other.get())) {
      return false;
    }
    return true;
  }

  // /**
  // * @param <PROPERTY> the generic type of the {@link AbstractProperty}.
  // * @param <BUILDER> the generic type of the {@link ObjectValidatorBuilder} for {@literal <VALUE>}.
  // * @param factory the {@link Function} to use as factory for the builder.
  // * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
  // * {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
  // * property with the configured validator.
  // */
  // protected <PROPERTY extends AbstractProperty<? extends V>, BUILDER extends ObjectValidatorBuilder<? extends V,
  // PropertyBuilder<PROPERTY>, ?>> BUILDER withValdidator(
  // Function<PropertyBuilder<PROPERTY>, BUILDER> factory) {
  //
  // PropertyBuilder<PROPERTY> parentBuilder = new PropertyBuilder<>();
  // BUILDER builder = factory.apply(parentBuilder);
  // parentBuilder.builder = builder;
  // return builder;
  // }

  // /**
  // * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
  // * {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
  // * property with the configured validator.
  // */
  // public abstract ObjectValidatorBuilder<? extends V, ? extends PropertyBuilder<? extends AbstractProperty<? extends
  // V>>, ?> withValdidator();

  // /**
  // * Implementation of {@link Builder} to {@link #build() build} a copy of the {@link AbstractProperty property}.
  // *
  // * @param <T> the generic type of the {@link AbstractProperty property}.
  // */
  // public class PropertyBuilder<T extends AbstractProperty<? extends V>> implements Builder<T> {
  //
  // private ObjectValidatorBuilder<?, ? extends PropertyBuilder<? extends T>, ?> builder;
  //
  // private boolean assignValue;
  //
  // /**
  // * The constructor.
  // */
  // public PropertyBuilder() {
  //
  // super();
  // }
  //
  // /**
  // * Also assign the {@link #get() value} from the original property. <br/>
  // * <b>ATTENTION:</b><br/>
  // * A {@link WritableProperty} may hold any kind of {@link #get() value} so the value may be mutable. If you are
  // * using this builder to create a copy of the property this might have undesired effects.
  // *
  // * @return this build instance for fluent API calls.
  // */
  // public PropertyBuilder<T> withValue() {
  //
  // this.assignValue = true;
  // return this;
  // }
  //
  // @Override
  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // public T build() {
  //
  // AbstractValidator<? super V> newValidator = (AbstractValidator) this.builder.build();
  // newValidator = AbstractProperty.this.validator.append((AbstractValidator) newValidator);
  // if (AbstractProperty.this.validator == newValidator) {
  // return (T) AbstractProperty.this;
  // }
  // AbstractProperty<V> copy = copy(newValidator);
  // if (this.assignValue) {
  // copy.setValue(get());
  // }
  // return (T) copy;
  // }
  // }

}
