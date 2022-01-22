/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import io.github.mmm.property.criteria.CriteriaAggregation;
import io.github.mmm.property.criteria.CriteriaAggregationOperator;
import io.github.mmm.value.CriteriaObject;

/**
 * Implementation of {@link CriteriaAggregation}.
 *
 * @param <R> type of the result value of the aggregation function.
 * @since 1.0.0
 */
public class CriteriaAggregationImpl<R> extends AbstractCriteriaExpression<R> implements CriteriaAggregation<R> {

  private final CriteriaAggregationOperator operator;

  private final CriteriaObject<?> firstArg;

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operator}.
   * @param property the {@link #getFirstArg() first argument}. May be {@code null} for
   *        {@link CriteriaAggregationOperator#COUNT COUNT(*)}.
   */
  public CriteriaAggregationImpl(CriteriaAggregationOperator operator, CriteriaObject<?> property) {

    super();
    this.operator = operator;
    this.firstArg = property;
  }

  @Override
  public CriteriaAggregationOperator getOperator() {

    return this.operator;
  }

  @Override
  public CriteriaObject<?> getFirstArg() {

    return this.firstArg;
  }

}
