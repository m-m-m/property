/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collection;
import java.util.List;

import io.github.mmm.value.CriteriaObject;

/**
 * Interface for a criteria expression. It combines an {@link #getOperator() operator} to invoke on the
 * {@link #getArgs() arguments}. Since many expressions are unary or binary there are also methods like
 * {@link #getArgCount()} as well as {@link #getFirstArg()} and {@link #getSecondArg()} to squeeze out the best
 * performance and prevent creating pointless arrays and {@link List}s.
 *
 * To build dynamic queries using {@link CriteriaExpression}s see {@code mmm-orm}.
 *
 * @param <R> type of the result value this expression evaluates to.
 * @since 1.0.0
 * @see CriteriaMarshalling
 */
public interface CriteriaExpression<R> extends CriteriaObject<R> {

  /**
   * @return the {@link PredicateOperator} used to compare the {@link #getArgs() arguments}.
   */
  CriteriaOperator getOperator();

  /**
   * @return the first argument of the {@link #getArgs() arguments}.
   * @see #getArgs()
   */
  CriteriaObject<?> getFirstArg();

  /**
   * @return the second argument of the {@link #getArgs() arguments} or {@code null} if not present (e.g. in case of
   *         unary {@link CriteriaExpression} like {@code NOT(expression)}).
   * @see #getArgs()
   */
  CriteriaObject<?> getSecondArg();

  /**
   * @return the {@link Collection} of arguments. Use {@link #getArgCount()} to check if only 1 or 2 arguments are
   *         present to avoid creation of {@link Collection}.
   */
  List<? extends CriteriaObject<?>> getArgs();

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
