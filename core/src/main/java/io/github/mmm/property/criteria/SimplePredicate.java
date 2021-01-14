/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of {@link CriteriaPredicate}
 *
 * @since 1.0.0
 */
public class SimplePredicate extends AbstractPredicate {

  private final Supplier<?> arg1;

  private final Supplier<?> arg2;

  /**
   * The constructor.
   *
   * @param arg1 the {@link #getArg1() first argument}.
   * @param operator the {@link #getOperator() operator}.
   * @param arg2 the {@link #getArg1() second argument}.
   */
  SimplePredicate(Supplier<?> arg1, PredicateOperator operator, Supplier<?> arg2) {

    super(operator);
    this.arg1 = arg1;
    this.arg2 = arg2;
  }

  @Override
  public Supplier<?> getArg1() {

    return this.arg1;
  }

  @Override
  public Supplier<?> getArg2() {

    return this.arg2;
  }

  @Override
  public Collection<? extends Supplier<?>> getArgs() {

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

}
