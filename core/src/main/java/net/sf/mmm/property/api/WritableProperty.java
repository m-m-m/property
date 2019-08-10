/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api;

import net.sf.mmm.marshall.UnmarshallableObject;
import net.sf.mmm.util.validation.api.ValidationFailure;
import net.sf.mmm.util.validation.api.ValueValidator;
import net.sf.mmm.value.observable.ObservableValue;
import net.sf.mmm.value.observable.WritableObservableValue;

/**
 * This is the interface for a generic property.
 *
 * @param <V> is the generic type of the {@link #getValue() value}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface WritableProperty<V> extends WritableObservableValue<V>, ReadableProperty<V>, UnmarshallableObject {

  /** Empty array instance. */
  WritableProperty<?>[] NO_PROPERTIES = new WritableProperty<?>[0];

  /**
   * Create a unidirection binding for this {@code Property}.
   *
   * @param observable the {@link ObservableValue} this {@code Property} should be bound to.
   */
  void bind(ObservableValue<? extends V> observable);

  /**
   * Remove the unidirectional binding for this {@code Property}.
   *
   * If the {@code Property} is not bound, calling this method has no effect.
   *
   * @see #bind(ObservableValue)
   */
  void unbind();

  /**
   * Can be used to check, if a {@code Property} is bound.
   *
   * @see #bind(ObservableValue)
   *
   * @return {@code true} if the {@code Property} is bound, {@code false} otherwise
   */
  boolean isBound();

  /**
   * Create a bidirectional binding between this {@code Property} and another one. Bidirectional bindings exists
   * independently of unidirectional bindings. So it is possible to add unidirectional binding to a property with
   * bidirectional binding and vice-versa. However, this practice is discouraged.
   * <p>
   * It is possible to have multiple bidirectional bindings of one Property.
   * <p>
   * JavaFX bidirectional binding implementation use weak listeners. This means bidirectional binding does not prevent
   * properties from being garbage collected.
   *
   * @param other the other {@code Property}
   * @throws NullPointerException if {@code other} is {@code null}
   * @throws IllegalArgumentException if {@code other} is {@code this}
   */
  void bindBidirectional(WritableProperty<V> other);

  /**
   * Remove a bidirectional binding between this {@code Property} and another one.
   *
   * If no bidirectional binding between the properties exists, calling this method has no effect.
   *
   * It is possible to unbind by a call on the second property. This code will work:
   *
   * <blockquote>
   *
   * <pre>
   * property1.bindBirectional(property2);
   * property2.unbindBidirectional(property1);
   * </pre>
   *
   * </blockquote>
   *
   * @param other the other {@code Property}
   * @throws NullPointerException if {@code other} is {@code null}
   * @throws IllegalArgumentException if {@code other} is {@code this}
   */
  void unbindBidirectional(WritableProperty<V> other);

  /**
   * @return {@code true} if this property is read-only and {@link #setValue(Object)} will fail with an exception,
   *         {@code false} otherwise.
   */
  boolean isReadOnly();

  /**
   * @return the {@link #isReadOnly() read only} view on this property.
   */
  WritableProperty<V> getReadOnly();

  /**
   * @return {@code true} if this property is mandatory (a {@link #getValue() value} of {@code null} is NOT
   *         {@link #isValid() valid}.
   */
  boolean isMandatory();

  /**
   * @see ValueValidator#validate(Object)
   *
   * @return the {@link ValidationFailure} or {@code null} if the {@link #getValue() value} is {@link #isValid() valid}.
   */
  ValidationFailure validate();

  /**
   * @see #validate()
   *
   * @return {@code true} if valid, {@code false} otherwise.
   */
  boolean isValid();

  /**
   * @param <P> the generic type of the property.
   * @param property the {@link WritableProperty property} to get as {@link #isReadOnly() read-only} view.
   * @return the {@link #getReadOnly() read-only view} of the given {@link WritableProperty property}.
   */
  @SuppressWarnings("unchecked")
  static <P extends WritableProperty<?>> P getReadOnly(P property) {

    if (property.isReadOnly()) {
      return property;
    }
    return (P) property.getReadOnly();
  }

}
