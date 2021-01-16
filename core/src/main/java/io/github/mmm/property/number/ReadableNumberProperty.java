/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.number;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.comparable.ReadableComparableProperty;
import io.github.mmm.property.criteria.AggregationOperator;
import io.github.mmm.property.criteria.CriteriaAggregation;
import io.github.mmm.value.observable.number.NumberExpression;

/**
 * {@link ReadableProperty} with {@link Number} {@link #get() value}.
 *
 * @see WritableNumberProperty
 *
 * @param <N> type of the internal numeric {@link #get() value} representation.
 *
 * @since 1.0.0
 */
public interface ReadableNumberProperty<N extends Number & Comparable<? super N>>
    extends ReadableComparableProperty<N>, NumberExpression<N> {

  /**
   * @return the {@link CriteriaAggregation} aggregating all values of this property using
   *         {@link AggregationOperator#SUM SUM}.
   */
  default CriteriaAggregation<N> sum() {

    return AggregationOperator.SUM.criteria(this);
  }

  /**
   * @return the {@link CriteriaAggregation} aggregating all values of this property using
   *         {@link AggregationOperator#AVG AVG}.
   */
  default CriteriaAggregation<N> avg() {

    return AggregationOperator.AVG.criteria(this);
  }

  /**
   * @return the {@link CriteriaAggregation} aggregating all values of this property using
   *         {@link AggregationOperator#MIN MIN}.
   */
  default CriteriaAggregation<N> min() {

    return AggregationOperator.MIN.criteria(this);
  }

  /**
   * @return the {@link CriteriaAggregation} aggregating all values of this property using
   *         {@link AggregationOperator#MAX MAX}.
   */
  default CriteriaAggregation<N> max() {

    return AggregationOperator.MAX.criteria(this);
  }

}
