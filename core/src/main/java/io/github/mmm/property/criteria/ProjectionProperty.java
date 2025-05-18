/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * Projection from a {@link #getSelection() selection} to a single {@link #getProperty() property} in a projection
 * ({@code SelectProjection}).
 *
 * @since 1.0.0
 * @param <V> type of the selection value.
 */
public class ProjectionProperty<V> implements CriteriaObject<V> {

  private final CriteriaObject<V> selection;

  private final PropertyPath<V> property;

  /**
   * The constructor.
   *
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   */
  public ProjectionProperty(CriteriaObject<V> selection, PropertyPath<V> property) {

    super();
    assert (!(property instanceof ReadableProperty) || !((ReadableProperty<?>) property).isReadOnly());
    this.selection = selection;
    this.property = property;
  }

  /**
   * @return the actual selection. Either a {@link ProjectionProperty} on a selected or joined {@code EntityBean} or an
   *         {@link CriteriaAggregation aggregation function}.
   */
  public CriteriaObject<V> getSelection() {

    return this.selection;
  }

  /**
   * @return the {@link ProjectionProperty} to the property of the projection bean.
   */
  public PropertyPath<V> getProperty() {

    return this.property;
  }

  /**
   * @param <V> type of the selection value.
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   * @return the new {@link ProjectionProperty}.
   */
  public static <V> ProjectionProperty<V> of(PropertyPath<V> selection, PropertyPath<V> property) {

    return new ProjectionProperty<>(selection, property);
  }

  /**
   * @param <V> type of the selection value.
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   * @return the new {@link ProjectionProperty}.
   */
  public static <V extends Number & Comparable<V>> ProjectionProperty<V> of(CriteriaAggregation<V> selection,
      PropertyPath<V> property) {

    return new ProjectionProperty<>(selection, property);
  }

}
