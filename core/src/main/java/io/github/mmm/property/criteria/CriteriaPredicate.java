/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Objects;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.criteria.impl.ConjunctionPredicate;
import io.github.mmm.property.criteria.impl.SimplePredicate;
import io.github.mmm.value.PropertyPath;

/**
 * {@link CriteriaExpression} that is a predicate {@link #get() evaluating} to a {@link Boolean}. Can be e.g. used in
 * WHERE or HAVING clauses of queries (see {@code mmm-entity-bean}).
 *
 * @since 1.0.0
 */
public interface CriteriaPredicate extends CriteriaExpression<Boolean>, BooleanSupplier {

  @Override
  PredicateOperator getOperator();

  /**
   * @return the negation of this {@link CriteriaPredicate}.
   */
  @Override
  CriteriaPredicate not();

  /**
   * @param predicate the {@link ConjunctionPredicate} to join this {@link CriteriaPredicate} using
   *        {@link PredicateOperator#AND logical AND}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  default CriteriaPredicate and(CriteriaPredicate predicate) {

    return new ConjunctionPredicate(PredicateOperator.AND, this, predicate);
  }

  /**
   * @param predicates the {@link ConjunctionPredicate}s to join this {@link CriteriaPredicate} using
   *        {@link PredicateOperator#AND logical AND}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  default CriteriaPredicate and(CriteriaPredicate... predicates) {

    int length = predicates.length;
    if (length == 0) {
      return this;
    }
    CriteriaPredicate[] args = new CriteriaPredicate[length + 1];
    args[0] = this;
    System.arraycopy(predicates, 0, args, 1, length);
    return new ConjunctionPredicate(PredicateOperator.AND, args);
  }

  /**
   * @param predicate the {@link ConjunctionPredicate} to join this {@link CriteriaPredicate} using
   *        {@link PredicateOperator#AND logical AND}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  default CriteriaPredicate or(CriteriaPredicate predicate) {

    return new ConjunctionPredicate(PredicateOperator.OR, this, predicate);
  }

  /**
   * @param predicates the {@link ConjunctionPredicate}s to join this {@link CriteriaPredicate} using
   *        {@link PredicateOperator#AND logical AND}.
   * @return the resulting {@link ConjunctionPredicate}.
   */
  default CriteriaPredicate or(CriteriaPredicate... predicates) {

    int length = predicates.length;
    if (length == 0) {
      return this;
    }
    CriteriaPredicate[] args = new CriteriaPredicate[length + 1];
    args[0] = this;
    System.arraycopy(predicates, 0, args, 1, length);
    return new ConjunctionPredicate(PredicateOperator.OR, args);
  }

  @Override
  CriteriaPredicate simplify();

  /**
   * @param <V> type of the {@code value}.
   * @param property the {@link ReadableProperty} to compare.
   * @param op the {@link PredicateOperator} for comparison.
   * @param value the value to compare with.
   * @return the resulting {@link CriteriaPredicate}.
   */
  static <V> CriteriaPredicate of(ReadableProperty<V> property, PredicateOperator op, V value) {

    Objects.requireNonNull(property);
    if (value == null) {
      if (op == PredicateOperator.EQ) {
        op = PredicateOperator.IS_NULL;
      } else if (op == PredicateOperator.NEQ) {
        op = PredicateOperator.IS_NOT_NULL;
      }
    }
    assert (!op.isConjunction());
    return new SimplePredicate(property, op, Literal.of(value));
  }

  /**
   * @param <V> type of the {@code value}.
   * @param property the {@link ReadableProperty} to compare.
   * @param op the {@link PredicateOperator} for comparison.
   * @param property2 the second {@link PropertyPath property} to compare with.
   * @return the resulting {@link CriteriaPredicate}.
   */
  static <V> CriteriaPredicate of(ReadableProperty<V> property, PredicateOperator op, PropertyPath<V> property2) {

    Objects.requireNonNull(property);
    Objects.requireNonNull(property2);
    assert (!op.isConjunction());
    return new SimplePredicate(property, op, property2);
  }

  /**
   * @param <V> type of the {@code value}.
   * @param value the value to compare with.
   * @param op the {@link PredicateOperator} for comparison.
   * @param property the {@link ReadableProperty} to compare.
   * @return the resulting {@link CriteriaPredicate}.
   */
  static <V> CriteriaPredicate of(V value, PredicateOperator op, ReadableProperty<V> property) {

    Objects.requireNonNull(value);
    assert (op == PredicateOperator.LIKE) || (op == PredicateOperator.NOT_LIKE);
    Objects.requireNonNull(property);
    return new SimplePredicate(Literal.of(value), op, property);
  }

}
