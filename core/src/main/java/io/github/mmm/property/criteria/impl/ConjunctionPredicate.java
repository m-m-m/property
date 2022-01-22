/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.mmm.property.criteria.BooleanSelection;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;

/**
 * {@link CriteriaPredicate} that aggregates a {@link #getArgs() collection of other predicates} using a
 * {@link PredicateOperator#isConjunction() conjunction} {@link #getOperator() operator}.
 *
 * @since 1.0.0
 */
public class ConjunctionPredicate extends AbstractCriteriaPredicate {

  private final List<BooleanSelection> args;

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operation}.
   * @param args the {@link #getArgs() arguments}.
   */
  public ConjunctionPredicate(PredicateOperator operator, BooleanSelection... args) {

    this(List.of(args), operator);
  }

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operation}.
   * @param args the {@link #getArgs() arguments}.
   */
  public ConjunctionPredicate(PredicateOperator operator, List<BooleanSelection> args) {

    this(Collections.unmodifiableList(args), operator);
  }

  /**
   * The constructor.
   *
   * @param args the {@link #getArgs() arguments}.
   * @param operator the {@link #getOperator() operation}.
   */
  private ConjunctionPredicate(List<BooleanSelection> args, PredicateOperator operator) {

    super(operator);
    if (!operator.isConjunction()) {
      throw new IllegalArgumentException("" + operator);
    }
    assert (!args.isEmpty());
    this.args = args;
  }

  @Override
  public BooleanSelection getFirstArg() {

    if (this.args.size() > 0) {
      return this.args.get(0);
    }
    return null;
  }

  @Override
  public BooleanSelection getSecondArg() {

    if (this.args.size() > 1) {
      return this.args.get(1);
    }
    return null;
  }

  @Override
  public List<BooleanSelection> getArgs() {

    return this.args;
  }

  @Override
  public CriteriaPredicate not() {

    return new ConjunctionPredicate(this.operator.not(), this.args);
  }

  @Override
  public CriteriaPredicate simplify() {

    List<BooleanSelection> newArgs = null;
    int size = this.args.size();
    for (int i = 0; i < size; i++) {
      BooleanSelection arg = this.args.get(i);
      BooleanSelection simplified = arg.simplify();
      if ((simplified instanceof ConjunctionPredicate)
          && (this.operator == ((ConjunctionPredicate) simplified).operator)) {
        newArgs = copyArgs(newArgs, i);
        newArgs.addAll(((ConjunctionPredicate) simplified).getArgs());
      } else if (simplified != arg) {
        newArgs = copyArgs(newArgs, i);
        newArgs.add(simplified);
      }
    }
    if (newArgs == null) {
      return this;
    }
    return new ConjunctionPredicate(this.operator, newArgs);
  }

  private List<BooleanSelection> copyArgs(List<BooleanSelection> newArgs, int limit) {

    if (newArgs == null) {
      newArgs = new ArrayList<>(this.args.size() + 2);
      for (int i = 0; i < limit; i++) {
        newArgs.add(this.args.get(i));
      }
    }
    return newArgs;
  }

}
