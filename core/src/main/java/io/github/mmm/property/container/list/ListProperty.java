/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.list;

import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.container.collection.CollectionProperty;
import io.github.mmm.value.observable.container.list.ChangeAwareList;
import io.github.mmm.value.observable.container.list.ChangeAwareLists;

/**
 * Implementation of {@link WritableListProperty}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class ListProperty<E> extends CollectionProperty<List<E>, E> implements WritableListProperty<E> {

  private List<E> value;

  private ChangeAwareList<E> changeAwareList;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public ListProperty(String name, WritableProperty<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ListProperty(String name, WritableProperty<E> valueProperty, PropertyMetadata<List<E>> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected List<E> doGet() {

    if (this.changeAwareList != null) {
      return this.changeAwareList;
    }
    return this.value;
  }

  @Override
  protected void doSet(List<E> newValue) {

    if (this.changeAwareList != null) {
      if (newValue == null) {
        this.changeAwareList.clear();
      } else {
        this.changeAwareList.setAll(newValue);
      }
    } else {
      this.value = newValue;
    }
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareList != null);
  }

  @Override
  public ChangeAwareList<E> getChangeAwareValue() {

    if (this.changeAwareList == null) {
      this.changeAwareList = ChangeAwareLists.of(this.value);
      this.changeAwareList.addListener(change -> {
        invalidateProperties();
        fireChange(change);
      });
    }
    return this.changeAwareList;
  }

  @Override
  protected Supplier<? extends List<E>> createReadOnlyExpression() {

    final List<E> readOnlyList;
    if (this.changeAwareList == null) {
      readOnlyList = ChangeAwareLists.ofUnmodifiable(this::getSafe);
      return () -> {
        List<E> result = get();
        if (result == null) {
          return null;
        }
        return readOnlyList;
      };
    } else {
      readOnlyList = ChangeAwareLists.ofUnmodifiable(this.changeAwareList);
      return () -> readOnlyList;
    }
  }

}
