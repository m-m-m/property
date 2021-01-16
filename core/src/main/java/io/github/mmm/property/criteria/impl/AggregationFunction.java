/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import io.github.mmm.property.criteria.AggregationOperator;
import io.github.mmm.property.criteria.CriteriaAggregation;
import io.github.mmm.value.PropertyPath;

/**
 * Implementation of {@link CriteriaAggregation}.
 *
 * @param <R> type of the result value of the aggregation function.
 * @since 1.0.0
 */
public class AggregationFunction<R extends Number> extends AbstractCriteriaExpression<R>
    implements CriteriaAggregation<R> {

  private final AggregationOperator operator;

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operator}.
   * @param path the {@link #getFirstArg() first argument}. May be {@code null} for {@link AggregationOperator#COUNT
   *        COUNT(*)}.
   */
  public AggregationFunction(AggregationOperator operator, PropertyPath<?> path) {

    super();
    this.operator = operator;
  }

  @Override
  public AggregationOperator getOperator() {

    return this.operator;
  }

  @Override
  public PropertyPath<?> getFirstArg() {

    return null;
  }

}
