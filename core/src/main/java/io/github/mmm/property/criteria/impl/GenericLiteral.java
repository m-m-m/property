/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.property.criteria.Literal;

/**
 * {@link Supplier} for a literal {@link #get() value}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public class GenericLiteral<V> implements Literal<V> {

  private final V value;

  /**
   * The constructor.
   *
   * @param value the literal value to {@link #get()}.
   */
  public GenericLiteral(V value) {

    super();
    Objects.requireNonNull(value);
    this.value = value;
  }

  @Override
  public V get() {

    return this.value;
  }

  @Override
  public String toString() {

    return toString(this.value);
  }

  private String toString(Object literalValue) {

    if (literalValue == null) {
      return "null";
    } else if (literalValue instanceof Supplier) {
      return toString(((Supplier<?>) literalValue).get());
    } else if (literalValue instanceof Boolean) {
      return BooleanLiteralImpl.toString(((Boolean) literalValue).booleanValue());
    } else if (literalValue instanceof Number) {
      return literalValue.toString();
    } else {
      return StringLiteral.toSql(this.value.toString());
    }
  }

}
