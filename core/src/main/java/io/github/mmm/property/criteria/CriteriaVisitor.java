/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collection;
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

    onOperator(expression.getOperator());
    int argCount = expression.getArgCount();
    if (argCount <= 2) {
      if (argCount > 0) {
        onArg(expression.getArg1());
        if (argCount == 2) {
          onArg(expression.getArg2());
        }
      }
    } else {
      Collection<? extends Supplier<?>> args = expression.getArgs();
      for (Supplier<?> arg : args) {
        onArg(arg);
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
   */
  default void onArg(Supplier<?> arg) {

    if (arg instanceof Literal) {
      onLiteral((Literal<?>) arg);
    } else if (arg instanceof PropertyPath) {
      onPropertyPath((PropertyPath<?>) arg);
    } else if (arg instanceof CriteriaExpression) {
      onExpression((CriteriaExpression<?>) arg);
    } else {
      onUndefinedArg(arg);
    }
  }

  /**
   * @param arg the undefined arg (if no {@link Literal}, {@link PropertyPath} or {@link CriteriaExpression}).
   * @see #onArg(Supplier)
   */
  default void onUndefinedArg(Supplier<?> arg) {

  }

  /**
   * @param property the {@link PropertyPath} to visit.
   * @see #onArg(Supplier)
   */
  default void onPropertyPath(PropertyPath<?> property) {

  }

  /**
   * @param literal the {@link Literal} to visit.
   * @see #onArg(Supplier)
   */
  default void onLiteral(Literal<?> literal) {

  }

}
