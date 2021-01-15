/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Collections;
import java.util.List;

/**
 * {@link CriteriaPredicate} that aggregates a {@link #getArgs() collection of other predicates} using a
 * {@link PredicateOperator#isConjunction() conjunction} {@link #getOperator() operator}.
 *
 * @since 1.0.0
 */
public class ConjunctionPredicate extends AbstractPredicate {

  private final List<CriteriaPredicate> args;

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operation}.
   * @param args the {@link #getArgs() arguments}.
   */
  public ConjunctionPredicate(PredicateOperator operator, CriteriaPredicate... args) {

    this(List.of(args), operator);
  }

  /**
   * The constructor.
   *
   * @param operator the {@link #getOperator() operation}.
   * @param args the {@link #getArgs() arguments}.
   */
  public ConjunctionPredicate(PredicateOperator operator, List<CriteriaPredicate> args) {

    this(Collections.unmodifiableList(args), operator);
  }

  /**
   * The constructor.
   *
   * @param args the {@link #getArgs() arguments}.
   * @param operator the {@link #getOperator() operation}.
   */
  private ConjunctionPredicate(List<CriteriaPredicate> args, PredicateOperator operator) {

    super(operator);
    if (!operator.isConjunction()) {
      throw new IllegalArgumentException("" + operator);
    }
    assert (!args.isEmpty());
    this.args = args;
  }

  @Override
  public CriteriaPredicate getArg1() {

    if (this.args.size() > 0) {
      return this.args.get(0);
    }
    return null;
  }

  @Override
  public CriteriaPredicate getArg2() {

    if (this.args.size() > 1) {
      return this.args.get(1);
    }
    return null;
  }

  @Override
  public List<CriteriaPredicate> getArgs() {

    return this.args;
  }

  @Override
  public CriteriaPredicate not() {

    return new ConjunctionPredicate(this.operator.not(), this.args);
  }

  /**
   * @param predicates the {@link ConjunctionPredicate}s to join using {@link PredicateOperator#AND logical AND}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  public static ConjunctionPredicate ofAnd(CriteriaPredicate... predicates) {

    return new ConjunctionPredicate(PredicateOperator.AND, predicates);
  }

  /**
   * @param predicates the {@link ConjunctionPredicate}s to join using {@link PredicateOperator#OR logical OR}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  public static ConjunctionPredicate ofOr(CriteriaPredicate... predicates) {

    return new ConjunctionPredicate(PredicateOperator.OR, predicates);
  }

}
