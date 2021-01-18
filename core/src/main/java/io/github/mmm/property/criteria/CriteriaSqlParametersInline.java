/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Objects;

import io.github.mmm.base.io.AppendableWriter;

/**
 *
 */
class CriteriaSqlParametersInline implements CriteriaSqlParameters {

  private static final CriteriaSqlParametersInline INSTANCE = new CriteriaSqlParametersInline();

  @Override
  public void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent) {

    out.write(Objects.toString(literal));
  }

  /**
   * @return the singleton instance of this {@link CriteriaSqlParametersInline}.
   */
  static CriteriaSqlParametersInline get() {

    return INSTANCE;
  }

}
