/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.Objects;

import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * A container for a {@link #getProperty() property} together with its {@link #getValue() value} to assign.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class PropertyAssignment<V> {

  /** An empty array of {@link PropertyAssignment}. */
  public static final PropertyAssignment<?>[] EMPTY_ARRAY = new PropertyAssignment<?>[0];

  private final PropertyPath<V> property;

  private final CriteriaObject<V> value;

  /**
   * The constructor.
   *
   * @param property the {@link #getProperty() property} to assign the {@code value} to.
   * @param value the {@link #getValue() value} to assign.
   */
  PropertyAssignment(PropertyPath<V> property, CriteriaObject<V> value) {

    super();
    Objects.requireNonNull(property, "property");
    this.property = property;
    this.value = value;
  }

  /**
   * @return property the {@link PropertyPath} to assign the {@code value} to.
   */
  public PropertyPath<V> getProperty() {

    return this.property;
  }

  /**
   * @return the {@link CriteriaObject} to assign as value. Typically a {@link Literal} value, but may also be a
   *         {@link CriteriaExpression} or {@link PropertyPath}.
   */
  public CriteriaObject<V> getValue() {

    return this.value;
  }

  @Override
  public String toString() {

    String v;
    if (this.value instanceof PropertyPath) {
      v = ((PropertyPath<?>) this.value).path();
    } else {
      v = Objects.toString(this.value);
    }
    return this.property.path() + "=" + v;
  }

  /**
   * @param <V> type of the {@link #getValue() value}.
   * @param property the {@link PropertyPath} to assign the {@code value} to.
   * @param value the {@link Literal#get() value} to assign.
   * @return the new {@link PropertyAssignment}.
   */
  public static <V> PropertyAssignment<V> of(PropertyPath<V> property, V value) {

    return of(property, Literal.of(value));
  }

  /**
   * @param <V> type of the {@link #getValue() value}.
   * @param property the {@link PropertyPath} to assign the {@code value} to.
   * @param value the {@link CriteriaObject} with the {@code value} to assign. Can be a {@link Literal},
   *        {@link PropertyPath property} (from another table), or an {@link CriteriaExpression expression}.
   * @return the new {@link PropertyAssignment}.
   */
  public static <V> PropertyAssignment<V> of(PropertyPath<V> property, CriteriaObject<V> value) {

    return new PropertyAssignment<>(property, value);
  }

  /**
   * Uses the {@link PropertyPath#get() current value} of the given {@link PropertyPath} as {@link #getValue() value to
   * assign}.
   *
   * @param <V> type of the {@link #getValue() value}.
   * @param property the {@link PropertyPath} to assign its {@link PropertyPath#get() current value}.
   * @return the new {@link PropertyAssignment}.
   */
  public static <V> PropertyAssignment<V> ofValue(PropertyPath<V> property) {

    return of(property, property.get());
  }

  /**
   * Uses the {@link PropertyPath#get() current value} of the given {@link PropertyPath properties} as
   * {@link #getValue() value to assign}.
   *
   * @param <V> type of the {@link #getValue() value}.
   * @param properties the {@link PropertyPath properties} to assign their * @param property the {@link PropertyPath} to
   *        assign its {@link PropertyPath#get() current values}..
   * @return the new {@link PropertyAssignment}.
   */
  @SuppressWarnings("unchecked")
  public static <V> PropertyAssignment<V>[] ofValues(PropertyPath<V>... properties) {

    PropertyAssignment<V>[] assignments = new PropertyAssignment[properties.length];
    for (int i = 0; i < properties.length; i++) {
      assignments[i] = ofValue(properties[i]);
    }
    return assignments;
  }

}
