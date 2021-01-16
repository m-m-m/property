/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.property.criteria.impl.AggregationFunction;
import io.github.mmm.value.PropertyPath;

/**
 * {@link CriteriaExpression} using an {@link AggregationOperator} such as {@link AggregationOperator#COUNT COUNT},
 * {@link AggregationOperator#SUM SUM}, {@link AggregationOperator#AVG AVG}, {@link AggregationOperator#MIN MIN},
 * {@link AggregationOperator#MAX MAX}, or {@link AggregationOperator#GROUP_CONCAT GROUP_CONCAT}.
 *
 * @param <R> type of the result value of the aggregation function.
 * @since 1.0.0
 */
public interface CriteriaAggregation<R extends Number> extends CriteriaExpression<R> {

  /**
   * {@link CriteriaAggregation} for {@code COUNT(*)}.
   */
  CriteriaAggregation<Integer> COUNT_ALL = new AggregationFunction<>(AggregationOperator.COUNT, null);

  @Override
  AggregationOperator getOperator();

  /**
   * In most cases {@link PropertyPath}{@code <R>} but in case of {@link AggregationOperator#COUNT COUNT} it can have
   * any value type.
   */
  @Override
  PropertyPath<?> getFirstArg();

  /**
   * @deprecated will always return {@code null}.
   */
  @Deprecated
  @Override
  default PropertyPath<?> getSecondArg() {

    return null;
  }

  /**
   * @deprecated will always return a singleton or empty {@link List}. Use {@link #getFirstArg()} instead.
   */
  @Deprecated
  @Override
  default List<? extends Supplier<?>> getArgs() {

    PropertyPath<?> arg = getFirstArg();
    if (arg == null) {
      return List.of();
    } else {
      return List.of(arg);
    }
  }

  /**
   * @deprecated will return {@code 0} if {@link #getFirstArg()} returns {@code null} (for {@code COUNT(*)} case) and
   *             otherwise always returns {@code 1}. Use {@link #getFirstArg()} instead.
   */
  @Deprecated
  @Override
  default int getArgCount() {

    PropertyPath<?> arg = getFirstArg();
    if (arg == null) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * @deprecated aggregations can not be simplified. Direct usage is pointless.
   */
  @Deprecated
  @Override
  default CriteriaAggregation<R> simplify() {

    return this;
  }

  /**
   * @param <R> type of the {@link PropertyPath} and the aggregated result.
   * @param operator the {@link AggregationOperator}.
   * @param property the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using the given
   *         {@link AggregationOperator}.
   */
  static <R extends Number> CriteriaAggregation<R> of(AggregationOperator operator, PropertyPath<R> property) {

    Objects.requireNonNull(operator, "operator");
    return operator.criteria(property);
  }

  /**
   * @param property the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using
   *         {@link AggregationOperator#COUNT COUNT}.
   */
  static CriteriaAggregation<Integer> count(PropertyPath<?> property) {

    Objects.requireNonNull(property, "property");
    return new AggregationFunction<>(AggregationOperator.COUNT, property);
  }

}
