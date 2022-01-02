package io.github.mmm.property.criteria;

import java.util.function.Supplier;

import io.github.mmm.value.PropertyPath;

/**
 * Projection from a {@link #getSelection() selection} to a single {@link #getProperty() property} in a projection
 * ({@code SelectProjection}).
 *
 * @since 1.0.0
 * @param <V> type of the {@link #get() property value}.
 */
public class ProjectionProperty<V> implements Supplier<V> {

  private final Supplier<V> selection;

  private final PropertyPath<V> property;

  /**
   * The constructor.
   *
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   */
  protected ProjectionProperty(Supplier<V> selection, PropertyPath<V> property) {

    super();
    this.selection = selection;
    this.property = property;
  }

  /**
   * @return the actual selection. Either a {@link ProjectionProperty} on a selected or joined {@code EntityBean} or an
   *         {@link CriteriaAggregation aggregation function}.
   */
  public Supplier<V> getSelection() {

    return this.selection;
  }

  /**
   * @return the {@link ProjectionProperty} to the property of the projection bean.
   */
  public PropertyPath<V> getProperty() {

    return this.property;
  }

  @Override
  public V get() {

    return this.selection.get();
  }

  /**
   * @param <V> type of the {@link #get() property value}.
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   * @return the new {@link ProjectionProperty}.
   */
  public static <V> ProjectionProperty<V> of(PropertyPath<V> selection, PropertyPath<V> property) {

    return new ProjectionProperty<>(selection, property);
  }

  /**
   * @param <V> type of the {@link #get() property value}.
   * @param selection the {@link #getSelection() selection}.
   * @param property the {@link #getProperty() projection property}.
   * @return the new {@link ProjectionProperty}.
   */
  public static <V extends Number> ProjectionProperty<V> of(CriteriaAggregation<V> selection,
      PropertyPath<V> property) {

    return new ProjectionProperty<>(selection, property);
  }

}
