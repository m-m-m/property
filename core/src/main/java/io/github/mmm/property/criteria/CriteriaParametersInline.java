/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Objects;

import io.github.mmm.base.io.AppendableWriter;

/**
 * Implementation of {@link CriteriaParameters} that directly outputs the {@link Literal} {@link Literal#get() value}.
 * Only used for simple output or debugging. Shall never be used directly for real databases for security (e.g. SQL
 * injection).
 *
 * @since 1.0.0
 */
class CriteriaParametersInline implements CriteriaParameters {

  private static final CriteriaParametersInline INSTANCE = new CriteriaParametersInline();

  @Override
  public void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent) {

    out.write(Objects.toString(literal));
  }

  /**
   * @return the singleton instance of this {@link CriteriaParametersInline}.
   */
  static CriteriaParametersInline get() {

    return INSTANCE;
  }

}
