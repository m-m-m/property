/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.set;

import java.util.Set;
import java.util.function.Supplier;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.container.collection.CollectionProperty;
import io.github.mmm.value.observable.container.set.ChangeAwareSet;
import io.github.mmm.value.observable.container.set.ChangeAwareSets;

/**
 * Implementation of {@link WritableSetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class SetProperty<E> extends CollectionProperty<Set<E>, E> implements WritableSetProperty<E> {

  private Set<E> value;

  private ChangeAwareSet<E> changeAwareSet;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public SetProperty(String name, WritableProperty<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public SetProperty(String name, WritableProperty<E> valueProperty, PropertyMetadata<Set<E>> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected Set<E> doGet() {

    if (this.changeAwareSet != null) {
      return this.changeAwareSet;
    }
    return this.value;
  }

  @Override
  protected void doSet(Set<E> newValue) {

    if (this.changeAwareSet != null) {
      if (newValue == null) {
        this.changeAwareSet.clear();
      } else {
        this.changeAwareSet.setAll(newValue);
      }
    } else {
      this.value = newValue;
    }
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareSet != null);
  }

  @Override
  public ChangeAwareSet<E> getChangeAwareValue() {

    if (this.changeAwareSet == null) {
      this.changeAwareSet = ChangeAwareSets.of(getOrCreate());
      this.changeAwareSet.addListener(change -> {
        invalidateProperties();
        fireChange(change);
      });
    }
    return this.changeAwareSet;
  }

  @Override
  protected Supplier<? extends Set<E>> createReadOnlyExpression() {

    final Set<E> readOnlySet;
    if (this.changeAwareSet == null) {
      readOnlySet = ChangeAwareSets.ofUnmodifiable(this::getSafe);
      return () -> {
        Set<E> result = get();
        if (result == null) {
          return null;
        }
        return readOnlySet;
      };
    } else {
      readOnlySet = ChangeAwareSets.ofUnmodifiable(this.changeAwareSet);
      return () -> readOnlySet;
    }
  }

}
