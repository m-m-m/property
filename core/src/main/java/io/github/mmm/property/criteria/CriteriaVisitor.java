/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.value.PropertyPath;

/**
 * Interface for visitor on {@link CriteriaExpression}. Override individual methods you are interested in. Typically do
 * something before and/or after delegating to parent method ({@code super.on...(...)}).
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
        onArg(expression, 0, expression.getFirstArg());
        if (argCount == 2) {
          onArg(expression, 1, expression.getSecondArg());
        }
      }
    } else {
      List<? extends Supplier<?>> args = expression.getArgs();
      assert (argCount == args.size());
      for (int i = 0; i < argCount; i++) {
        onArg(expression, i, args.get(i));
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
   * @param expression the {@link CriteriaExpression} to owning the given {@code arg}.
   * @param i the {@link List#get(int) index} of {@code arg} in the {@link CriteriaExpression#getArgs() arguments}.
   * @param arg the {@link Supplier} {@link CriteriaExpression#getArgs() argument} to visit.
   */
  default void onArg(CriteriaExpression<?> expression, int i, Supplier<?> arg) {

    if (arg instanceof Literal) {
      onLiteral((Literal<?>) arg);
    } else if (arg instanceof PropertyPath) {
      onPropertyPath((PropertyPath<?>) arg);
    } else if (arg instanceof CriteriaExpression) {
      onExpression((CriteriaExpression<?>) arg, expression);
    } else {
      onUndefinedArg(arg);
    }
  }

  /**
   * @param arg the undefined arg (if no {@link Literal}, {@link PropertyPath} or {@link CriteriaExpression}).
   * @see #onArg(CriteriaExpression, int, Supplier)
   */
  default void onUndefinedArg(Supplier<?> arg) {

  }

  /**
   * @param property the {@link PropertyPath} to visit.
   * @see #onArg(CriteriaExpression, int, Supplier)
   */
  default void onPropertyPath(PropertyPath<?> property) {

  }

  /**
   * @param literal the {@link Literal} to visit.
   * @see #onArg(CriteriaExpression, int, Supplier)
   */
  default void onLiteral(Literal<?> literal) {

  }

}
