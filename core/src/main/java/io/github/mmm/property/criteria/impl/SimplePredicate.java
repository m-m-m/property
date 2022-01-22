/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.List;

import io.github.mmm.property.criteria.BooleanLiteral;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.value.CriteriaObject;

/**
 * Implementation of {@link CriteriaPredicate}
 *
 * @since 1.0.0
 */
public class SimplePredicate extends AbstractCriteriaPredicate {

  /** {@link SimplePredicate} that always evaluates to {@code true}. */
  public static final SimplePredicate ALWAYS = new SimplePredicate(BooleanLiteral.TRUE, PredicateOperator.EQ,
      BooleanLiteral.TRUE);

  /** {@link SimplePredicate} that always evaluates to {@code false}. */
  public static final SimplePredicate NEVER = new SimplePredicate(BooleanLiteral.TRUE, PredicateOperator.EQ,
      BooleanLiteral.FALSE);

  private final CriteriaObject<?> arg1;

  private final CriteriaObject<?> arg2;

  /**
   * The constructor.
   *
   * @param arg1 the {@link #getFirstArg() first argument}.
   * @param operator the {@link #getOperator() operator}.
   * @param arg2 the {@link #getFirstArg() second argument}.
   */
  public SimplePredicate(CriteriaObject<?> arg1, PredicateOperator operator, CriteriaObject<?> arg2) {

    super(operator);
    if (operator.isConjunction()) {
      throw new IllegalStateException(operator.toString());
    }
    this.arg1 = arg1;
    this.arg2 = arg2;
    assert ((arg2 == null) == operator.isUnary());
  }

  @Override
  public CriteriaObject<?> getFirstArg() {

    return this.arg1;
  }

  @Override
  public CriteriaObject<?> getSecondArg() {

    return this.arg2;
  }

  @Override
  public List<? extends CriteriaObject<?>> getArgs() {

    if (this.arg2 == null) {
      return List.of(this.arg1);
    }
    return List.of(this.arg1, this.arg2);
  }

  @Override
  public int getArgCount() {

    if (this.arg2 == null) {
      return 1;
    }
    return 2;
  }

  @Override
  public CriteriaPredicate not() {

    if (this == ALWAYS) {
      return NEVER;
    } else if (this == NEVER) {
      return ALWAYS;
    }
    PredicateOperator inverseOperator = this.operator.not();
    if (inverseOperator == null) {
      if (this.operator == PredicateOperator.NOT) {
        if ((this.arg2 == null) && (this.arg1 instanceof CriteriaPredicate)) {
          return (CriteriaPredicate) this.arg1;
        }
      }
      return null; // illegal state?
    } else {
      return new SimplePredicate(this.arg1, inverseOperator, this.arg2);
    }
  }

  @Override
  public CriteriaPredicate simplify() {

    if (this.operator == PredicateOperator.NOT) {
      assert (this.arg2 == null);
      if (this.arg1 instanceof CriteriaPredicate) {
        return ((CriteriaPredicate) this.arg1).not();
      }
    }
    return this;
  }

}
