/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.comparable.ReadableComparableProperty;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.value.observable.string.StringExpression;

/**
 * {@link ReadableProperty} with {@link String} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableStringProperty extends ReadableComparableProperty<String>, StringExpression {

  /**
   * @param other the literal {@link String} pattern to match using {@link PredicateOperator#LIKE LIKE}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate like(String other) {

    return CriteriaPredicate.of(this, PredicateOperator.LIKE, other);
  }

  /**
   * @param other the literal {@link String} pattern to match using {@link PredicateOperator#NOT_LIKE NOT LIKE}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate notLike(String other) {

    return CriteriaPredicate.of(this, PredicateOperator.NOT_LIKE, other);
  }

  /**
   * @param other the literal {@link String} to match against a pattern from a database property using
   *        {@link PredicateOperator#LIKE LIKE}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate inverseLike(String other) {

    return CriteriaPredicate.of(other, PredicateOperator.LIKE, this);
  }

  /**
   * @param other the literal {@link String} to match against a pattern from a database property using
   *        {@link PredicateOperator#NOT_LIKE NOT LIKE}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate inverseNotLike(String other) {

    return CriteriaPredicate.of(other, PredicateOperator.NOT_LIKE, this);
  }

}
