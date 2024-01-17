/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.comparable;

import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * Interface for an object typed with a {@link Comparable} value that allows to produce .
 *
 * @param <V> type of the value.
 * @since 1.0.0
 */
public interface ComparablePredicateSupport<V extends Comparable<? super V>> extends CriteriaObject<V> {

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#LT < (less-than)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate lt(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.LT, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same value type to compare with using
   *        {@link PredicateOperator#LT < (less-than)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate lt(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.LT, other);
  }

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#LE <= (less-or-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate le(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.LE, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same value type to compare with using
   *        {@link PredicateOperator#LE <= (less-or-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate le(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.LE, other);
  }

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#GT >= (greater-than)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate gt(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.GT, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same value type to compare with using
   *        {@link PredicateOperator#GT >= (greater-than)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate gt(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.GT, other);
  }

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#GE >= (greater-or-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate ge(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.GE, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same value type to compare with using
   *        {@link PredicateOperator#GE >= (greater-or-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate ge(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.GE, other);
  }

}
