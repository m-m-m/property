/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.base.io.AppendableWriter;

/**
 * Interface to handle {@link Literal} {@link Literal#get() values} as parameters in SQL statements.
 *
 * @since 1.0.0
 */
public interface CriteriaSqlParameters {

  /**
   * @param literal the {@link Literal} to {@link AppendableWriter#write(String) write} as SQL and to collect as
   *        parameter.
   * @param out the {@link AppendableWriter} where to {@link AppendableWriter#write(String) write} the SQL to.
   * @param parent the parent {@link CriteriaExpression} containing the {@link Literal}.
   */
  void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent);

  /**
   * @param <P> the {@link CriteriaSqlParameters}-implementation to cast to.
   * @return the casted {@link CriteriaSqlParameters}.
   */
  @SuppressWarnings("unchecked")
  default <P extends CriteriaSqlParameters> P cast() {

    return (P) this;
  }

}
