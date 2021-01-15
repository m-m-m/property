/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Interface for a search criteria. Use {@link CriteriaSqlFormatter} and its sub-classes to convert to SQL and
 * {@link CriteriaMarshalling} to (un)marshall to JSON, XML, etc.
 *
 * @param <R> type of the result value this expression evaluates to.
 * @since 1.0.0
 */
public interface CriteriaExpression<R> extends Supplier<R> {

  /**
   * @return the {@link PredicateOperator} used to compare the {@link #getArgs() arguments}.
   */
  Operator getOperator();

  /**
   * @return the first argument of the {@link #getArgs() arguments}.
   * @see #getArgs()
   */
  Supplier<?> getArg1();

  /**
   * @return the second argument of the {@link #getArgs() arguments} or {@code null} if not present (e.g. in case of
   *         unary {@link CriteriaExpression} like {@code NOT(expression)}).
   * @see #getArgs()
   */
  Supplier<?> getArg2();

  /**
   * @return the {@link Collection} of arguments. Use {@link #getArgCount()} to check if only 1 or 2 arguments are
   *         present to avoid creation of {@link Collection}.
   */
  Collection<? extends Supplier<?>> getArgs();

  /**
   * @return the {@link Collection#size() number} of {@link #getArgs() arguments}.
   */
  default int getArgCount() {

    return getArgs().size();
  }

}
