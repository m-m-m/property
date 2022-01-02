/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.property.criteria.impl.AggregationFunction;
import io.github.mmm.value.PropertyPath;

/**
 * {@link Operator} for an {@link CriteriaAggregation}.
 *
 * @since 1.0.0
 */
public class AggregationOperator extends Operator {

  /**
   * Operator to summarize all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>SUM(e.Price)</em>).
   */
  public static final AggregationOperator SUM = new AggregationOperator("SUM");

  /**
   * Operator to calculate the average of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>AVG(e.Price)</em>).
   */
  public static final AggregationOperator AVG = new AggregationOperator("AVG");

  /**
   * Operator to determine the minimum of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>MIN(e.Price)</em>).
   */
  public static final AggregationOperator MIN = new AggregationOperator("MIN");

  /**
   * Operator to determine the maximum of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>MAX(e.Price)</em>).
   */
  public static final AggregationOperator MAX = new AggregationOperator("MAX");

  /**
   * Operator to count all entities ({@code COUNT(*)} or only those where the {@link CriteriaAggregation#getFirstArg()
   * property} is not {@code null} (e.g. <em>COUNT(e.Price)</em>).
   */
  public static final AggregationOperator COUNT = new AggregationOperator("COUNT");

  /**
   * Operator to concatenate all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>GROUP_CONCAT(e.Name)</em>).
   */
  public static final AggregationOperator GROUP_CONCAT = new AggregationOperator("GROUP_CONCAT");

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   */
  protected AggregationOperator(String syntax) {

    super(syntax, null, false);
  }

  @Override
  public boolean isUnary() {

    return true;
  }

  @Override
  public AggregationOperator not() {

    return (AggregationOperator) super.not();
  }

  /**
   * @deprecated use {@link #criteria(PropertyPath)} directly as only a single specific argument can be provided here.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Deprecated
  @Override
  public CriteriaExpression<?> criteria(List<Supplier<?>> args) {

    int size = args.size();
    if (size == 0) {
      return criteria((PropertyPath) null);
    } else if (size == 1) {
      return criteria((PropertyPath) args.get(0));
    } else {
      throw new IllegalArgumentException("" + size);
    }
  }

  /**
   * @param <V> type of the {@link PropertyPath} and the aggregated result.
   * @param property the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using this
   *         {@link AggregationOperator}.
   */
  public <V> CriteriaAggregation<V> criteria(PropertyPath<V> property) {

    Objects.requireNonNull(property, "property");
    return new AggregationFunction<>(this, property);
  }

  /**
   * @param syntax the {@link #getSyntax() syntax} of the requested {@link AggregationOperator}.
   * @return the predefined {@link AggregationOperator} or {@code null} if no such operator exists.
   */
  public static AggregationOperator of(String syntax) {

    Operator op = Operator.of(syntax);
    if (op instanceof AggregationOperator) {
      return (AggregationOperator) op;
    }
    return null;
  }

  /**
   * Ensure class-loading and initialization.
   */
  static void load() {

  }

}
