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
import io.github.mmm.value.converter.TypeMapper;
import io.github.mmm.value.observable.ObservableValue;

/**
 * This is the interface for a generic property.
 *
 * @param <V> type of the {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadableProperty<V> extends ObservableValue<V>, PropertyPath<V>, MarshallableObject, Validatable,
    AttributeReadOnly, Comparable<ReadableProperty<?>> {

  /**
   * @return the name of the property. By convention it should start with a {@link Character#isUpperCase(char) capital}
   *         letter followed by alpha-numeric characters. The name of a single property must especially not contain the
   *         dot character (.) that is used to separate segments in a {@link PropertyPath path}.
   */
  @Override
  String getName();

  /**
   * @return the {@link #getName() name} including the {@link #parentPath() owner(s)}.
   */
  default String getQualifiedName() {

    return getQualifiedName(true);
  }

  /**
   * @param fixed {@code true} to use {@link #path(boolean) fixed path}, {@code false} otherwise.
   * @return the {@link #getName() name} including the {@link #parentPath() owner(s)}.
   */
  default String getQualifiedName(boolean fixed) {

    String path = "";
    AttributeReadOnly lock = getMetadata().getLock();
    if (lock instanceof ReadablePath) {
      path = ((ReadablePath) lock).path(fixed);
    }
    if (path.isEmpty()) {
      return getName();
    }
    return path + "." + getName();
  }

  @Override
  default ReadablePath parentPath() {

    AttributeReadOnly lock = getMetadata().getLock();
    if (lock instanceof ReadablePath path) {
      return path;
    }
    return PropertyPath.super.parentPath();
  }

  /**
   * @return {@code true} if valid, {@code false} otherwise.
   * @see #validate()
   */
  boolean isValid();

  /**
   * @return the optional {@link TypeMapper} allowing to map the {@link #get() value} to a simple standard type(s) (e.g.
   *         {@link String} or {@link Number}). This allows build-in support for custom value types to support mapping
   *         (e.g. to database).
   */
  default TypeMapper<V, ?> getTypeMapper() {

    return null;
  }

  /**
   * @return {@code true} if transient (e.g. computed and therefore not to be marshalled), {@code false} otherwise.
   */
  default boolean isTransient() {

    return getMetadata().isTransient();
  }

  /**
   * @return {@code true} if the {@link #get() property value} is mandatory and will not accept {@code null} or empty
   *         values), {@code false} otherwise.
   * @see #getMetadata()
   * @see PropertyMetadata#getValidator()
   * @see io.github.mmm.validation.Validator#isMandatory()
   */
  default boolean isMandatory() {

    return getMetadata().getValidator().isMandatory();
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

  /**
   * Similar to {@link #equals(Object)} but may be overridden for more semantic comparison (e.g. for bean property).
   *
   * @param other the other {@link ReadableProperty property} to compare with.
   * @return {@code true} if this property is equal to the given property.
   * @see #equals(Object)
   */
  default boolean isEqual(ReadableProperty<?> other) {

    return equals(other);
  }

}
