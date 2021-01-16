/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.comparable;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.criteria.CriteriaOrdering;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.property.object.ReadableSimpleProperty;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.observable.comparable.ComparableExpression;

/**
 * {@link ReadableProperty} with {@link Comparable} {@link #get() value}.
 *
 * @param <V> type of the {@link #get() value}.
 * @since 1.0.0
 */
public interface ReadableComparableProperty<V extends Comparable<? super V>>
    extends ReadableSimpleProperty<V>, ComparableExpression<V> {

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#LT < (less-than)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate lt(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.LT, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#LT < (less-than)}.
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
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#LE <= (less-or-equal)}.
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
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#GT >= (greater-than)}.
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
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#GE >= (greater-or-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate ge(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.GE, other);
  }

  /**
   * @return the {@link CriteriaOrdering} to sort by this property {@link SortOrder#ASC ascending}.
   */
  default CriteriaOrdering asc() {

    return new CriteriaOrdering(this, SortOrder.ASC);
  }

  /**
   * @return the {@link CriteriaOrdering} to sort by this property {@link SortOrder#DESC descending}.
   */
  default CriteriaOrdering desc() {

    return new CriteriaOrdering(this, SortOrder.DESC);
  }

}
