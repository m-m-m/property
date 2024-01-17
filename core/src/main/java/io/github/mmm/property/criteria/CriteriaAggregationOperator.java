/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.Objects;

import io.github.mmm.property.criteria.impl.CriteriaAggregationImpl;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * {@link CriteriaOperator} for an {@link CriteriaAggregation}.
 *
 * @since 1.0.0
 */
public class CriteriaAggregationOperator extends CriteriaOperator {

  /**
   * Operator to summarize all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>SUM(e.Price)</em>).
   */
  public static final CriteriaAggregationOperator SUM = new CriteriaAggregationOperator("SUM");

  /**
   * Operator to calculate the average of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>AVG(e.Price)</em>).
   */
  public static final CriteriaAggregationOperator AVG = new CriteriaAggregationOperator("AVG");

  /**
   * Operator to determine the minimum of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>MIN(e.Price)</em>).
   */
  public static final CriteriaAggregationOperator MIN = new CriteriaAggregationOperator("MIN");

  /**
   * Operator to determine the maximum of all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>MAX(e.Price)</em>).
   */
  public static final CriteriaAggregationOperator MAX = new CriteriaAggregationOperator("MAX");

  /**
   * Operator to count all entities ({@code COUNT(*)} or only those where the {@link CriteriaAggregation#getFirstArg()
   * property} is not {@code null} (e.g. <em>COUNT(e.Price)</em>).
   */
  public static final CriteriaAggregationOperator COUNT = new CriteriaAggregationOperator("COUNT");

  /**
   * Operator to concatenate all values of a {@link CriteriaAggregation#getFirstArg() property} (e.g.
   * <em>GROUP_CONCAT(e.Name)</em>).
   */
  public static final CriteriaAggregationOperator GROUP_CONCAT = new CriteriaAggregationOperator("GROUP_CONCAT");

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   */
  protected CriteriaAggregationOperator(String syntax) {

    super(syntax, null, false);
  }

  @Override
  public boolean isUnary() {

    return true;
  }

  @Override
  public int getPriority() {

    // can not occur without parenthesis
    return 0;
  }

  @Override
  public CriteriaAggregationOperator not() {

    return (CriteriaAggregationOperator) super.not();
  }

  /**
   * @deprecated use {@link #criteria(PropertyPath)} directly as only a single specific argument can be provided here.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Deprecated
  @Override
  public CriteriaExpression<?> expression(List<CriteriaObject<?>> args) {

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
   *         {@link CriteriaAggregationOperator}.
   */
  public <V extends Comparable<? super V>> CriteriaAggregation<V> criteria(PropertyPath<V> property) {

    Objects.requireNonNull(property, "property");
    return new CriteriaAggregationImpl<>(this, property);
  }

  /**
   * @param <V> type of the {@link PropertyPath} and the aggregated result.
   * @param nestedAggregation the {@link PropertyPath} to aggregate.
   * @return the {@link CriteriaAggregation} aggregating the given {@link PropertyPath property} using this
   *         {@link CriteriaAggregationOperator}.
   */
  public <V extends Comparable<? super V>> CriteriaAggregation<V> criteria(CriteriaAggregation<V> nestedAggregation) {

    Objects.requireNonNull(nestedAggregation, "property");
    return new CriteriaAggregationImpl<>(this, nestedAggregation);
  }

  /**
   * @param syntax the {@link #getSyntax() syntax} of the requested {@link CriteriaAggregationOperator}.
   * @return the predefined {@link CriteriaAggregationOperator} or {@code null} if no such operator exists.
   */
  public static CriteriaAggregationOperator of(String syntax) {

    CriteriaOperator op = CriteriaOperator.of(syntax);
    if (op instanceof CriteriaAggregationOperator) {
      return (CriteriaAggregationOperator) op;
    }
    return null;
  }

  /**
   * Ensure class-loading and initialization.
   */
  static void load() {

  }

}
