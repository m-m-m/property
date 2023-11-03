/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import io.github.mmm.marshall.MarshallingObject;
import io.github.mmm.value.ReadableValue;
import io.github.mmm.value.observable.WritableObservableValue;

/**
 * A {@link ReadableProperty property} with write access (e.g. {@link #set(Object)}). However, it can still be
 * {@link #isReadOnly() read-only} preventing modifications.
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritableProperty<V> extends WritableObservableValue<V>, ReadableProperty<V>, MarshallingObject {

  /** Empty array instance. */
  WritableProperty<?>[] NO_PROPERTIES = new WritableProperty<?>[0];

  /**
   * @return the {@link #isReadOnly() read only} view on this property.
   * @see #isReadOnly()
   * @see #getReadOnly(WritableProperty)
   */
  WritableProperty<V> getReadOnly();

  /**
   * @param newName the new {@link #getName() name}. May be {@code null} to keep the current name.
   * @param newMetadata the new {@link #getMetadata() metadata}. May be {@code null} to keep the current metadata.
   * @return a new instance of this property with empty {@link #get() value} no bindings and the given parameters
   *         applied.
   */
  WritableProperty<V> copy(String newName, PropertyMetadata<V> newMetadata);

  /**
   * @param other the {@link ReadableValue property} to copy the {@link #get() value} from.
   */
  default void copyValue(ReadableValue<V> other) {

    set(other.get());
  }

  /**
   * @param <P> type of the property.
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

  /**
   * @param <P> type of the property.
   * @param property the {@link WritableProperty property} to copy.
   * @return a {@link #copy(String, PropertyMetadata) copy} of the given {@code property} with empty value and the given
   *         parameters applied.
   */
  static <P extends WritableProperty<?>> P copy(P property) {

    return copy(property, null);
  }

  /**
   * @param <P> type of the property.
   * @param property the {@link WritableProperty property} to copy.
   * @param newName the new {@link #getName() name}. May be {@code null} to keep the current name.
   * @return a {@link #copy(String, PropertyMetadata) copy} of the given {@code property} with empty value and the given
   *         parameters applied.
   */
  @SuppressWarnings("unchecked")
  static <P extends WritableProperty<?>> P copy(P property, String newName) {

    return (P) property.copy(newName, null);
  }

  /**
   * @param <V> type of the {@link #get() properties value}.
   * @param <P> type of the property.
   * @param property the {@link WritableProperty property} to copy.
   * @param newName the new {@link #getName() name}. May be {@code null} to keep the current name.
   * @param newMetadata the new {@link #getMetadata() metadata}. May be {@code null} to keep the current metadata.
   * @return a {@link #copy(String, PropertyMetadata) copy} of the given {@code property} with empty value and the given
   *         parameters applied.
   */
  @SuppressWarnings("unchecked")
  static <V, P extends WritableProperty<V>> P copy(P property, String newName, PropertyMetadata<V> newMetadata) {

    return (P) property.copy(newName, newMetadata);
  }

}
