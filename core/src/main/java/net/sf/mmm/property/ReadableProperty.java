/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property;

import net.sf.mmm.marshall.MarshallableObject;
import net.sf.mmm.validation.Validatable;
import net.sf.mmm.value.PropertyPath;
import net.sf.mmm.value.ReadableTypedValue;
import net.sf.mmm.value.observable.ObservableValue;

/**
 * This is the interface for a generic property.
 *
 * @param <V> type of the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public interface ReadableProperty<V>
    extends ObservableValue<V>, ReadableTypedValue<V>, PropertyPath<V>, MarshallableObject, Validatable {

  /**
   * @return the name of the property. By convention it should start with a {@link Character#isUpperCase(char) capital}
   *         letter followed by alpha-numeric characters. The name of a single property must especially not contain the
   *         dot character (.) that is used to separate segments in a {@link PropertyPath path}.
   */
  @Override
  String getName();

  /**
   * @return {@code true} if valid, {@code false} otherwise.
   * @see #validate()
   */
  boolean isValid();

  /**
   * @return {@code true} if this property is immutable (read-only) and {@link WritableProperty#setValue(Object)
   *         modifications} will fail with an exception, {@code false} otherwise.
   * @see WritableProperty#getReadOnly()
   */
  boolean isReadOnly();

  /**
   * @return the {@link PropertyMetadata metadata} of this property.
   */
  PropertyMetadata<V> getMetadata();

}
