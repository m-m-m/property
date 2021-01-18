/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.time.temporal.Temporal;
import java.util.function.Supplier;

import io.github.mmm.property.criteria.impl.GenericLiteral;
import io.github.mmm.property.criteria.impl.NumberLiteral;
import io.github.mmm.property.criteria.impl.StringLiteral;
import io.github.mmm.property.criteria.impl.TemporalLiteral;

/**
 * {@link Supplier} for a literal {@link #get() value}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public interface Literal<V> extends Supplier<V> {

  /**
   * @param value the {@link #get() value}.
   * @return the {@link Literal}.
   */
  static BooleanLiteral of(boolean value) {

    if (value) {
      return BooleanLiteral.TRUE;
    }
    return BooleanLiteral.FALSE;
  }

  /**
   * @param value the {@link #get() value}.
   * @return the {@link Literal} or {@code null} if {@code value} is {@code null}.
   */
  public static BooleanLiteral of(Boolean value) {

    if (value == null) {
      return null;
    }
    return of(value.booleanValue());
  }

  /**
   * @param value the {@link #get() value}.
   * @return the {@link Literal}.
   */
  static Literal<String> of(String value) {

    if (value == null) {
      return null;
    }
    return new StringLiteral(value);
  }

  /**
   * @param <N> the {@link Number} type.
   * @param value the {@link #get() value}.
   * @return the {@link Literal}.
   */
  static <N extends Number> Literal<N> of(N value) {

    if (value == null) {
      return null;
    }
    return new NumberLiteral<>(value);
  }

  /**
   * @param <T> the {@link Temporal} type.
   * @param value the {@link #get() value}.
   * @return the {@link Literal}.
   */
  static <T extends Temporal> Literal<T> of(T value) {

    if (value == null) {
      return null;
    }
    return new TemporalLiteral<>(value);
  }

  /**
   * @param <V> type of the {@link #get() value}.
   * @param value the {@link #get() value}.
   * @return the {@link Literal}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  static <V> Literal<V> of(V value) {

    Literal literal;
    if (value == null) {
      literal = null;
    } else if (value instanceof String) {
      literal = of((String) value);
    } else if (value instanceof Boolean) {
      literal = of((Boolean) value);
    } else if (value instanceof Number) {
      literal = of((Number) value);
    } else if (value instanceof Temporal) {
      literal = of((Temporal) value);
    } else {
      literal = new GenericLiteral<>(value);
    }
    return literal;
  }

}
