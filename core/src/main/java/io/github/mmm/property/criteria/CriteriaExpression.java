/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collection;
import java.util.List;

import io.github.mmm.value.CriteriaSelection;

/**
 * Interface for a search criteria. Can be e.g. used to build dynamic queries (see {@code mmm-entity-db}). Use
 * {@link CriteriaFormatter} and its sub-classes to convert to database syntax (e.g. SQL) and
 * {@link CriteriaMarshalling} to (un)marshall to JSON, XML, etc.
 *
 * @param <R> type of the result value this expression evaluates to.
 * @since 1.0.0
 */
public interface CriteriaExpression<R> extends CriteriaSelection<R> {

  /**
   * @return the {@link PredicateOperator} used to compare the {@link #getArgs() arguments}.
   */
  Operator getOperator();

  /**
   * @return the first argument of the {@link #getArgs() arguments}.
   * @see #getArgs()
   */
  CriteriaSelection<?> getFirstArg();

  /**
   * @return the second argument of the {@link #getArgs() arguments} or {@code null} if not present (e.g. in case of
   *         unary {@link CriteriaExpression} like {@code NOT(expression)}).
   * @see #getArgs()
   */
  CriteriaSelection<?> getSecondArg();

  /**
   * @return the {@link Collection} of arguments. Use {@link #getArgCount()} to check if only 1 or 2 arguments are
   *         present to avoid creation of {@link Collection}.
   */
  List<? extends CriteriaSelection<?>> getArgs();

  /**
   * @return the {@link Collection#size() number} of {@link #getArgs() arguments}.
   */
  default int getArgCount() {

    return getArgs().size();
  }

  /**
   * Simplifies this expression. The following table shows some examples:
   * <table border="1">
   * <tr>
   * <th>{@code exp}</th>
   * <th>{@code exp.simplify()}</th>
   * </tr>
   * <tr>
   * <td>NOT(NOT(e1))</td>
   * <td>e1</td>
   * </tr>
   * <tr>
   * <td>NOT(IS NOT NULL(e.Name))</td>
   * <td>IS NULL(e.Name)</td>
   * </tr>
   * <tr>
   * <td>e1 AND (e2 AND e3)</td>
   * <td>e1 AND e2 AND e3</td>
   * </tr>
   * <tr>
   * <td>e1 AND (e2 OR e3)</td>
   * <td>e1 AND (e2 OR e3)</td>
   * </tr>
   * </table>
   * Here we assume that {@code e1}, {@code e2}, and {@code e3} are simplified expressions. The last example shows an
   * expression that can not be simplified anymore.
   *
   * @return a simplified form of this expression or this instance itself if already simplified.
   */
  CriteriaExpression<R> simplify();

}
