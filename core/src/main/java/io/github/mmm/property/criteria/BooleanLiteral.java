/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.property.criteria.impl.BooleanLiteralImpl;

/**
 * {@link Literal} for {@link Boolean} value.
 *
 * @since 1.0.0
 */
public interface BooleanLiteral extends BooleanSupplier, Literal<Boolean> {

  /** {@link BooleanLiteral} for {@link Boolean#TRUE}. */
  BooleanLiteral TRUE = BooleanLiteralImpl.TRUE;

  /** {@link BooleanLiteral} for {@link Boolean#FALSE}. */
  BooleanLiteral FALSE = BooleanLiteralImpl.FALSE;

  @Override
  default BooleanLiteral simplify() {

    return this;
  }

  @Override
  default BooleanLiteral not() {

    if (this == FALSE) {
      return TRUE;
    }
    return FALSE;
  }

}
