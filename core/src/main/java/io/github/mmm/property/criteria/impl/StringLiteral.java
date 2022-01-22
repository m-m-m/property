/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.property.criteria.Literal;

/**
 * {@link Supplier} for a literal {@link #get() value}.
 *
 * @since 1.0.0
 */
public final class StringLiteral implements Literal<String> {

  private final String value;

  private String formatted;

  /**
   * The constructor.
   *
   * @param value the literal value to {@link #get()}.
   */
  public StringLiteral(String value) {

    super();
    Objects.requireNonNull(value);
    this.value = value;
  }

  @Override
  public String get() {

    return this.value;
  }

  /**
   * @param string the {@link String} value.
   * @return the given {@link String} as SQL literal.
   */
  public static String toString(String string) {

    return "'" + string.replace("'", "\'\'") + "'";
  }

  @Override
  public String toString() {

    if (this.formatted == null) {
      this.formatted = toString(this.value);
    }
    return this.formatted;
  }

}
