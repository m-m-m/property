/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.time;

import java.time.Instant;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableObjectValue;
import net.sf.mmm.property.api.ReadableObjectProperty;
import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.base.AbstractBooleanBinding;
import net.sf.mmm.util.reflect.api.GenericType;
import net.sf.mmm.util.reflect.impl.SimpleGenericTypeImpl;

/**
 * This is the interface for a {@link ReadableProperty} of the {@link #getValue() value}-{@link #getType() type}
 * {@link Boolean}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableInstantProperty extends ReadableObjectProperty<Instant> {

  /** @see #getType */
  GenericType<Instant> TYPE = new SimpleGenericTypeImpl<>(Instant.class);

  @Override
  default GenericType<Instant> getType() {

    return TYPE;
  }

  /**
   * @param other the {@code ObservableObjectValue} to compare.
   * @return a new {@link BooleanBinding} that holds {@code true} if the {@link #getValue() value} of this property is
   *         {@link Instant#isBefore(Instant) before} the {@link #getValue() value} of another the given
   *         {@link ObservableObjectValue}.
   */
  default BooleanBinding isBefore(ObservableObjectValue<Instant> other) {

    return new AbstractBooleanBinding(this, other) {

      @Override
      protected boolean computeValue() {

        Instant date1 = ReadableInstantProperty.this.getValue();
        if (date1 == null) {
          return false;
        }
        Instant date2 = other.getValue();
        if (date2 == null) {
          return false;
        }
        return date1.isBefore(date2);
      }
    };
  }

  /**
   * @param other the {@code ObservableObjectValue} to compare.
   * @return a new {@link BooleanBinding} that holds {@code true} if the {@link #getValue() value} of this property is
   *         {@link Instant#isAfter(Instant) after} the {@link #getValue() value} of another the given
   *         {@link ObservableObjectValue}.
   */
  default BooleanBinding isAfter(ObservableObjectValue<Instant> other) {

    return new AbstractBooleanBinding(this, other) {

      @Override
      protected boolean computeValue() {

        Instant date1 = ReadableInstantProperty.this.getValue();
        if (date1 == null) {
          return false;
        }
        Instant date2 = other.getValue();
        if (date2 == null) {
          return false;
        }
        return date1.isAfter(date2);
      }
    };
  }

}
