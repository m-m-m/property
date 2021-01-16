/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.Objects;

import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;

/**
 * Implementation of {@link CriteriaPredicate}
 *
 * @since 1.0.0
 */
public abstract class AbstractPredicate extends AbstractCriteriaExpression<Boolean> implements CriteriaPredicate {

  /** @see #getOperator() */
  protected final PredicateOperator operator;

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operator}.
   */
  public AbstractPredicate(PredicateOperator operator) {

    super();
    Objects.requireNonNull(operator);
    this.operator = operator;
  }

  @Override
  public PredicateOperator getOperator() {

    return this.operator;
  }

  @Override
  public Boolean get() {

    // TODO Auto-generated method stub
    return null;
  }

}
