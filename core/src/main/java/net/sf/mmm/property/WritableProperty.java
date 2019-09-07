/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property;

import net.sf.mmm.marshall.UnmarshallableObject;
import net.sf.mmm.value.observable.WritableObservableValue;

/**
 * A {@link ReadableProperty property} with write access (e.g. {@link #setValue(Object)}). However, it can still be
 * {@link #isReadOnly() read-only} preventing modifications.
 *
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface WritableProperty<V> extends WritableObservableValue<V>, ReadableProperty<V>, UnmarshallableObject {

  /** Empty array instance. */
  WritableProperty<?>[] NO_PROPERTIES = new WritableProperty<?>[0];

  /**
   * @return the {@link #isReadOnly() read only} view on this property.
   * @see #isReadOnly()
   * @see #getReadOnly(WritableProperty)
   */
  WritableProperty<V> getReadOnly();

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

}
