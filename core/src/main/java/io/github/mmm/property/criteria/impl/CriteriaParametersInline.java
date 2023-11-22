/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

import io.github.mmm.base.io.AppendableWriter;
import io.github.mmm.property.criteria.CriteriaExpression;
import io.github.mmm.property.criteria.CriteriaParameter;
import io.github.mmm.property.criteria.CriteriaParameters;
import io.github.mmm.property.criteria.Literal;

/**
 * Implementation of {@link CriteriaParameters} that directly outputs the {@link Literal} {@link Literal#get() value}.
 * Only used for simple output or debugging. Shall never be used directly for real databases for security (e.g. SQL
 * injection).
 *
 * @since 1.0.0
 */
public class CriteriaParametersInline implements CriteriaParameters<CriteriaParameter<?>> {

  private static final CriteriaParametersInline INSTANCE = new CriteriaParametersInline();

  @Override
  public void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent) {

    out.write(Objects.toString(literal));
  }

  @Override
  public Iterator<CriteriaParameter<?>> iterator() {

    return Collections.emptyIterator();
  }

  /**
   * @return the singleton instance of this {@link CriteriaParametersInline}.
   */
  public static CriteriaParametersInline get() {

    return INSTANCE;
  }

}
