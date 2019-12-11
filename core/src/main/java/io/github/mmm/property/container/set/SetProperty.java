/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.set;

import java.util.Set;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.container.collection.CollectionProperty;
import io.github.mmm.value.observable.container.set.ChangeAwareSet;
import io.github.mmm.value.observable.container.set.ChangeAwareSets;
import io.github.mmm.value.observable.container.set.SetChangeListener;

/**
 * Implementation of {@link WritableSetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class SetProperty<E> extends CollectionProperty<Set<E>, E> implements WritableSetProperty<E> {

  private final SetChangeListener<E> setChangeListener = change -> {
    invalidateProperties();
    fireChange(change);
  };

  private Set<E> value;

  private ChangeAwareSet<E> changeAwareSet;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public SetProperty(String name, Property<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public SetProperty(String name, Property<E> valueProperty, PropertyMetadata<Set<E>> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected Set<E> doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Set<E> newValue) {

    this.value = newValue;
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareSet != null);
  }

  @Override
  public ChangeAwareSet<E> getChangeAwareValue() {

    if (this.changeAwareSet == null) {
      this.changeAwareSet = ChangeAwareSets.of(getOrCreate());
      this.changeAwareSet.addListener(this.setChangeListener);
    }
    return this.changeAwareSet;
  }

}
