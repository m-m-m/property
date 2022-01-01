/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.base.exception.ReadOnlyException;
import io.github.mmm.base.text.CaseHelper;
import io.github.mmm.marshall.Marshalling;
import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;
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
      this.metadata = PropertyMetadataNone.get();
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
  protected void setWithChange(V oldValue, V value) {

    super.setWithChange(oldValue, value);
    clearValidationResult();
  }

  @Override
  protected final void requireWritable() throws IllegalStateException {

    if (isReadOnly()) {
      throw new ReadOnlyException("Property " + getName() + " is readonly and cannot be modified.");
    }
    super.requireWritable();
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
   * changed (from an external call) and is not {@link #isValueMutable() mutable}.
   */
  protected void clearValidationResult() {

    this.validationResult = null;
  }

  @Override
  public final ValidationResult validate() {

    if ((this.validationResult == null) || isValueMutable()) {
      this.validationResult = doValidate(get(), getName());
    }
    return this.validationResult;
  }

  /**
   * @see #validate()
   * @param source the {@link ValidationResult#getSource() validation source}.
   * @return the {@link ValidationResult result of the validation}.
   */
  public ValidationResult doValidate(String source) {

    return doValidate(get(), source);
  }

  /**
   * Called from {@link #validate()} in case re-validation is required.
   *
   * @param v the {@link #get() value} to validate.
   * @param source the {@link ValidationResult#getSource() validation source}.
   * @return the {@link ValidationResult result of the validation}.
   */
  protected ValidationResult doValidate(V v, String source) {

    ValidationResult result = this.metadata.getValidator().validate(v, source);
    if (v instanceof Validatable) {
      result = result.add(((Validatable) v).validate());
    }
    return result;
  }

  /**
   * @return {@code true} if the {@link #getValueClass() value type} of this property is mutable (e.g.
   *         {@link java.util.Collection} or {@link java.util.Map}), {@code false} otherwise (immutable datatype such as
   *         {@link String}, {@link Boolean}, {@link Number}, {@link java.time.temporal.Temporal}, etc.).
   */
  public boolean isValueMutable() {

    return false;
  }

  @Override
  public WritableProperty<V> getReadOnly() {

    if (this.readOnlyProperty == null) {
      if (this.metadata.getExpression() != null) {
        this.readOnlyProperty = this;
      } else {
        Property<V> copy = copy(null, null);
        copy.bindInternal(this);
        copy.readOnlyProperty = copy;
        this.readOnlyProperty = copy;
      }
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
    return this.metadata.getLock().isReadOnly();
  }

  /**
   * @return {@code true} if the {@link #get() value} of this property is sensitive and should not be written to
   *         {@link #toString()}, logs, etc., {@code false} otherwise.
   */
  protected boolean isSensitive() {

    String nameLowerCase = CaseHelper.toLowerCase(this.name);
    if (nameLowerCase.endsWith("password") || nameLowerCase.endsWith("credential") || nameLowerCase.endsWith("secret")
        || nameLowerCase.endsWith("passphrase") || nameLowerCase.equals("pin")) {
      return true;
    }
    return false;
  }

  @Override
  public void toString(StringBuilder sb) {

    sb.append(this.name);
    sb.append('=');
    if (isSensitive()) {
      sb.append("**********");
    } else {
      sb.append(get());
    }
  }

  @Override
  public final void writeObject(StructuredWriter writer, Object object) {

    if (object != this) {
      throw new IllegalStateException();
    }
    Marshalling<V> marshalling = this.metadata.getMarshalling();
    if (marshalling == null) {
      write(writer);
    } else {
      marshalling.writeObject(writer, get());
    }
  }

  @Override
  public void write(StructuredWriter writer) {

    writer.writeValue(get());
  }

  @Override
  public final Property<V> readObject(StructuredReader reader) {

    Marshalling<V> marshalling = this.metadata.getMarshalling();
    if (marshalling == null) {
      read(reader);
    } else {
      V value = marshalling.readObject(reader);
      set(value);
    }
    return this;
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

}
