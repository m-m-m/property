/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.comparable;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.criteria.CriteriaOrdering;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.property.object.ReadableSimpleProperty;
import io.github.mmm.value.observable.comparable.ComparableExpression;

/**
 * {@link ReadableProperty} with {@link Comparable} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableComparableProperty<V extends Comparable<? super V>>
    extends ReadableSimpleProperty<V>, ComparableExpression<V> {

  default CriteriaPredicate lt(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.LT, other);
  }

  default CriteriaPredicate le(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.LE, other);
  }

  default CriteriaPredicate gt(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.GT, other);
  }

  default CriteriaPredicate ge(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.GE, other);
  }

  default CriteriaOrdering asc() {

    return new CriteriaOrdering(this, SortOrder.ASC);
  }

  default CriteriaOrdering desc() {

    return new CriteriaOrdering(this, SortOrder.DESC);
  }

}
