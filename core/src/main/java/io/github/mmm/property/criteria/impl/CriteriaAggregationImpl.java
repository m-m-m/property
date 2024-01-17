/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import io.github.mmm.property.criteria.CriteriaAggregation;
import io.github.mmm.property.criteria.CriteriaAggregationOperator;
import io.github.mmm.value.CriteriaObject;

/**
 * Implementation of {@link CriteriaAggregation}.
 *
 * @param <V> type of the result value of the aggregation function.
 * @since 1.0.0
 */
public class CriteriaAggregationImpl<V extends Comparable<? super V>> extends AbstractCriteriaExpression<V>
    implements CriteriaAggregation<V> {

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
