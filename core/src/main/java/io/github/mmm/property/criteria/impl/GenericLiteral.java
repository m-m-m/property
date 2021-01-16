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

    return StringLiteral.toSql(this.value.toString());
  }

}
