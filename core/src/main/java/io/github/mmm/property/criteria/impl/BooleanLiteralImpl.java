/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import io.github.mmm.property.criteria.BooleanLiteral;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.Literal;

/**
 * {@link Literal} for {@link Boolean} {@link #get() value}. Also implements {@link CriteriaPredicate} to be used in
 * {@link ConjunctionPredicate}.
 *
 * @since 1.0.0
 */
public class BooleanLiteralImpl implements BooleanLiteral {

  /** {@link Literal} for {@link Boolean#TRUE}. */
  public static final BooleanLiteralImpl TRUE = new BooleanLiteralImpl(Boolean.TRUE);

  /** {@link Literal} for {@link Boolean#FALSE}. */
  public static final BooleanLiteralImpl FALSE = new BooleanLiteralImpl(Boolean.FALSE);

  private final Boolean value;

  /**
   * The constructor.
   *
   * @param value
   */
  private BooleanLiteralImpl(Boolean value) {

    super();
    this.value = value;
  }

  @Override
  public Boolean get() {

    return this.value;
  }

  @Override
  public String toString() {

    return toString(this == TRUE);
  }

  static String toString(boolean booleanValue) {

    if (booleanValue) {
      return "TRUE";
    } else {
      return "FALSE";
    }
  }

}
