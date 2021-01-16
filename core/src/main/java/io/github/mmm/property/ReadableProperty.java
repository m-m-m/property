/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import io.github.mmm.marshall.MarshallableObject;
import io.github.mmm.property.criteria.CriteriaAggregation;
import io.github.mmm.property.criteria.CriteriaPredicate;
import io.github.mmm.property.criteria.PredicateOperator;
import io.github.mmm.validation.Validatable;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.ReadablePath;
import io.github.mmm.value.TypedPropertyPath;
import io.github.mmm.value.observable.ObservableValue;

/**
 * This is the interface for a generic property.
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableProperty<V>
    extends ObservableValue<V>, TypedPropertyPath<V>, MarshallableObject, Validatable, AttributeReadOnly {

  /**
   * @return the name of the property. By convention it should start with a {@link Character#isUpperCase(char) capital}
   *         letter followed by alpha-numeric characters. The name of a single property must especially not contain the
   *         dot character (.) that is used to separate segments in a {@link PropertyPath path}.
   */
  @Override
  String getName();

  @Override
  default String path() {

    String path = getName();
    AttributeReadOnly lock = getMetadata().getLock();
    if (lock instanceof ReadablePath) {
      String prefix = ((ReadablePath) lock).path();
      if (prefix != null) {
        path = prefix + path;
      }
    }
    return path;
  }

  /**
   * @return {@code true} if valid, {@code false} otherwise.
   * @see #validate()
   */
  boolean isValid();

  /**
   * @return {@code true} if transient (e.g. computed and therefore not to be marshalled), {@code false} otherwise.
   */
  default boolean isTransient() {

    return getMetadata().isTransient();
  }

  /**
   * @return the {@link PropertyMetadata metadata} of this property.
   */
  PropertyMetadata<V> getMetadata();

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#EQ = (equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate eq(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.EQ, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#EQ = (equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate eq(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.EQ, other);
  }

  /**
   * @param other the literal value to compare with using {@link PredicateOperator#NEQ != (not-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate neq(V other) {

    return CriteriaPredicate.of(this, PredicateOperator.NEQ, other);
  }

  /**
   * @param other the other {@link PropertyPath property} of the same {@link #getValueClass() value type} to compare
   *        with using {@link PredicateOperator#NEQ = (not-equal)}.
   * @return the resulting {@link CriteriaPredicate}.
   */
  default CriteriaPredicate neq(PropertyPath<V> other) {

    return CriteriaPredicate.of(this, PredicateOperator.NEQ, other);
  }

  /**
   * @return the {@link CriteriaAggregation} counting all values of this property that are not {@code null}.
   */
  default CriteriaAggregation<Integer> count() {

    return CriteriaAggregation.count(this);
  }

}
