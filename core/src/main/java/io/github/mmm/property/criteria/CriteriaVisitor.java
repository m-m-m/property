/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.value.PropertyPath;

/**
 * Interface for visitor on {@link CriteriaExpression}. Override individual methods you are interested in. Typically do
 * something before and/or after delegating to parent method ({@code super.on...(...)}).
 *
 * @since 1.0.0
 */
public interface CriteriaVisitor {

  /**
   * @param expression the {@link CriteriaExpression} to visit.
   * @return this visitor itself for fluent API calls.
   */
  default CriteriaVisitor onExpression(CriteriaExpression<?> expression) {

    return onExpression(expression, null);
  }

  /**
   * @param expression the {@link CriteriaExpression} to visit.
   * @param parent the parent {@link CriteriaExpression} or {@code null} if {@code expression} is the root
   *        {@link CriteriaExpression}.
   * @return this visitor itself for fluent API calls.
   */
  default CriteriaVisitor onExpression(CriteriaExpression<?> expression, CriteriaExpression<?> parent) {

    onOperator(expression.getOperator());
    int argCount = expression.getArgCount();
    if (argCount <= 2) {
      if (argCount > 0) {
        onArg(expression.getFirstArg(), 0, expression);
        if (argCount == 2) {
          onArg(expression.getSecondArg(), 1, expression);
        }
      }
    } else {
      List<? extends Supplier<?>> args = expression.getArgs();
      assert (argCount == args.size());
      for (int i = 0; i < argCount; i++) {
        onArg(args.get(i), i, expression);
      }
    }
    return this;
  }

  /**
   * @param operator the {@link Operator} to visit.
   */
  default void onOperator(Operator operator) {

  }

  /**
   * @param arg the {@link Supplier} {@link CriteriaExpression#getArgs() argument} to visit.
   * @param i the {@link List#get(int) index} of {@code arg} in the {@link CriteriaExpression#getArgs() arguments}.
   * @param parent the parent {@link CriteriaExpression} to owning the given {@code arg}.
   */
  default void onArg(Supplier<?> arg, int i, CriteriaExpression<?> parent) {

    if (arg instanceof Literal) {
      onLiteral((Literal<?>) arg, i, parent);
    } else if (arg instanceof PropertyPath) {
      onPropertyPath((PropertyPath<?>) arg, i, parent);
    } else if (arg instanceof CriteriaExpression) {
      onExpression((CriteriaExpression<?>) arg, parent);
    } else {
      onUndefinedArg(arg, i, parent);
    }
  }

  /**
   * @param arg the undefined arg (if no {@link Literal}, {@link PropertyPath} or {@link CriteriaExpression}).
   * @param i the {@link List#get(int) index} of {@code arg} in the {@link CriteriaExpression#getArgs() arguments}.
   * @param parent the parent {@link CriteriaExpression} to owning the given {@link Supplier}.
   * @see #onArg(Supplier, int, CriteriaExpression)
   */
  default void onUndefinedArg(Supplier<?> arg, int i, CriteriaExpression<?> parent) {

  }

  /**
   * @param property the {@link PropertyPath} to visit.
   * @param i the {@link List#get(int) index} of {@code arg} in the {@link CriteriaExpression#getArgs() arguments}.
   * @param parent the parent {@link CriteriaExpression} to owning the given {@link PropertyPath}.
   * @see #onArg(Supplier, int, CriteriaExpression)
   */
  default void onPropertyPath(PropertyPath<?> property, int i, CriteriaExpression<?> parent) {

  }

  /**
   * @param literal the {@link Literal} to visit.
   * @param i the {@link List#get(int) index} of {@code arg} in the {@link CriteriaExpression#getArgs() arguments}.
   * @param parent the parent {@link CriteriaExpression} to owning the given {@link Literal}.
   * @see #onArg(Supplier, int, CriteriaExpression)
   */
  default void onLiteral(Literal<?> literal, int i, CriteriaExpression<?> parent) {

  }

}
