/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.Objects;

import io.github.mmm.property.comparable.ComparablePredicateSupport;
import io.github.mmm.property.criteria.impl.CriteriaAggregationImpl;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * {@link CriteriaExpression} using an {@link CriteriaAggregationOperator} such as
 * {@link CriteriaAggregationOperator#COUNT COUNT}, {@link CriteriaAggregationOperator#SUM SUM},
 * {@link CriteriaAggregationOperator#AVG AVG}, {@link CriteriaAggregationOperator#MIN MIN},
 * {@link CriteriaAggregationOperator#MAX MAX}, or {@link CriteriaAggregationOperator#GROUP_CONCAT GROUP_CONCAT}. A
 * {@link CriteriaAggregation} can only have one {@link #getFirstArg() argument} (and {@link #COUNT_ALL} has none).
 *
 * @param <V> type of the result value of the aggregation function.
 * @since 1.0.0
 */
public interface CriteriaAggregation<V extends Comparable<? super V>>
    extends CriteriaExpression<V>, ComparablePredicateSupport<V> {

  /**
   * {@link CriteriaAggregation} for {@code COUNT(*)}.
   */
  CriteriaAggregation<Integer> COUNT_ALL = new CriteriaAggregationImpl<>(CriteriaAggregationOperator.COUNT, null);

  @Override
  CriteriaAggregationOperator getOperator();

  /**
   * In most cases {@link PropertyPath}{@code <R>} but may also be something else. In case of
   * {@link CriteriaAggregationOperator#COUNT COUNT} the generic {@code <R>} would be bound to {@link Long} and the the
   * argument can have any type. Further aggregation functions can be nested (e.g. "MAX(AVG(price))" if used in
   * combination with "GROUP BY").
   */
  @Override
  CriteriaObject<?> getFirstArg();

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
  default List<? extends CriteriaObject<?>> getArgs() {

    CriteriaObject<?> arg = getFirstArg();
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

    CriteriaObject<?> arg = getFirstArg();
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
  default CriteriaAggregation<V> simplify() {

    return this;
  }

  /**
   * @param <R> type of the {@link PropertyPath} and the aggregated result.
   * @param operator the {@link CriteriaAggregationOperator}.
   * @param property the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using the given
   *         {@link CriteriaAggregationOperator}.
   */
  static <R extends Comparable<? super R>> CriteriaAggregation<R> of(CriteriaAggregationOperator operator,
      PropertyPath<R> property) {

    Objects.requireNonNull(operator, "operator");
    return operator.criteria(property);
  }

  /**
   * @param <R> type of the {@link PropertyPath} and the aggregated result.
   * @param operator the {@link CriteriaAggregationOperator}.
   * @param nestedAggregation the nested {@link CriteriaAggregation} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link CriteriaAggregation} using the given
   *         {@link CriteriaAggregationOperator}.
   */
  static <R extends Comparable<? super R>> CriteriaAggregation<R> of(CriteriaAggregationOperator operator,
      CriteriaAggregation<R> nestedAggregation) {

    Objects.requireNonNull(operator, "operator");
    return operator.criteria(nestedAggregation);
  }

  /**
   * @param property the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using
   *         {@link CriteriaAggregationOperator#COUNT COUNT}.
   */
  static CriteriaAggregation<Integer> count(PropertyPath<?> property) {

    Objects.requireNonNull(property, "property");
    return new CriteriaAggregationImpl<>(CriteriaAggregationOperator.COUNT, property);
  }

}
