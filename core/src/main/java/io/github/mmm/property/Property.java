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

  @Override
  public int compareTo(ReadableProperty<?> otherProperty) {

    if (otherProperty == null) {
      return 1;
    }
    return this.name.compareTo(otherProperty.getName());
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
   * changed (from an external call) and {@link #isValueMutable() is not mutable}.
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
        // TODO might be incorrect since lock can still point to writable bean, must be bullet-proof
        this.readOnlyProperty = this;
      } else {
        Property<V> copy = copy(null, this.metadata.withLock(null).withExpression(createReadOnlyExpression()));
        copy.makeReadOnly();
        this.readOnlyProperty = copy;
      }
    }
    return this.readOnlyProperty;
  }

  /**
   * @return the {@link PropertyMetadata#getExpression() expression} to {@link #get() get the value} of a
   *         {@link #getReadOnly() read-only property view}. By default we assume that the {@link #get() property value}
   *         is immutable. However, for {@link Property properties} where this is not the case (e.g.
   *         {@link io.github.mmm.property.container.ContainerProperty}), this needs to be overridden.
   */
  protected Supplier<? extends V> createReadOnlyExpression() {

    return this::get;
  }

  @Override
  protected void makeReadOnly() {

    super.makeReadOnly();
    this.readOnlyProperty = this;
  }

  @Override
  public final boolean isReadOnly() {

    if (this.readOnlyProperty == this) {
      return true;
    } else if (this.metadata.getExpression() != null) {
      return true;
    } else if (isBoundOneWay()) {
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
      readValue(reader);
    } else {
      V value = marshalling.readObject(reader);
      set(value);
    }
    return this;
  }

  /**
   * @return this {@link Property} itself. Can be ignored and no need to write code that updates the containing object
   *         with the result of this method.
   */
  @Override
  public final Property<V> read(StructuredReader reader) {

    readValue(reader);
    return this;
  }

  /**
   * Reads the {@link #get() value} of this {@link Property} from the given {@link StructuredReader} and
   * {@link #set(Object) sets} it.
   *
   * @param reader the {@link StructuredReader} to read the value from.
   * @see #read(StructuredReader)
   */
  protected void readValue(StructuredReader reader) {

    V value = reader.readValue(getValueClass());
    set(value);
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
