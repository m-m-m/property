/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

import io.github.mmm.base.exception.RuntimeIoException;
import io.github.mmm.value.PropertyPath;

/**
 * Base-class for a search criteria.
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

  /**
   * @param pathFormatter the custom {@link Function} to format instances of {@link PropertyPath}.
   * @return the {@link #toString() string representation} of this object.
   */
  default String toString(Function<PropertyPath<?>, String> pathFormatter) {

    StringBuilder sb = new StringBuilder();
    toString(sb, pathFormatter);
    return sb.toString();
  }

  /**
   * @param buffer the {@link Appendable} to write to.
   * @param pathFormatter the custom {@link Function} to format instances of {@link PropertyPath}.
   */
  default void toString(Appendable buffer, Function<PropertyPath<?>, String> pathFormatter) {

    try {
      if (pathFormatter == null) {
        pathFormatter = p -> p.getName();
      }
      Operator op = getOperator();
      int argCount = getArgCount();
      if (argCount <= 2) {
        buffer.append(formatArg(getArg1(), pathFormatter));
        buffer.append(' ');
        buffer.append(op.toString());
        if (argCount == 2) {
          buffer.append(' ');
          buffer.append(formatArg(getArg2(), pathFormatter));
        }
      } else {
        Collection<? extends Supplier<?>> args = getArgs();
        buffer.append(op.toString());
        buffer.append('(');
        String separator = "";
        for (Supplier<?> arg : args) {
          buffer.append(separator);
          buffer.append(formatArg(arg, pathFormatter));
          separator = ",";
        }
        buffer.append(')');
      }
    } catch (IOException e) {
      throw new RuntimeIoException(e);
    }
  }

  private String formatArg(Supplier<?> arg, Function<PropertyPath<?>, String> pathFormatter) {

    if (arg == null) {
      return "null";
    } else if (arg instanceof PropertyPath) {
      return pathFormatter.apply((PropertyPath<?>) arg);
    }
    return arg.toString();
  }

}
