/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container.list;

import java.util.List;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.container.collection.CollectionProperty;
import io.github.mmm.value.observable.container.list.ChangeAwareList;
import io.github.mmm.value.observable.container.list.ChangeAwareLists;
import io.github.mmm.value.observable.container.list.ListChangeListener;

/**
 * Implementation of {@link WritableListProperty}.
 *
 * @param <E> type of the {@link List#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class ListProperty<E> extends CollectionProperty<List<E>, E> implements WritableListProperty<E> {

  private final ListChangeListener<E> listChangeListener = change -> {
    invalidateProperties();
    fireChange(change);
  };

  private List<E> value;

  private ChangeAwareList<E> changeAwareList;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public ListProperty(String name, Property<E> valueProperty) {

    super(name, valueProperty);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ListProperty(String name, Property<E> valueProperty, PropertyMetadata<List<E>> metadata) {

    super(name, valueProperty, metadata);
  }

  @Override
  protected List<E> doGet() {

    return this.value;
  }

  @Override
  protected void doSet(List<E> newValue) {

    this.value = newValue;
  }

  @Override
  protected void setWithChange(List<E> oldValue, List<E> value) {

    super.setWithChange(oldValue, value);
    if (this.changeAwareList != null) {
      this.changeAwareList.setAll(value);
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
      this.changeAwareList.addListener(this.listChangeListener);
    }
    return this.changeAwareList;
  }

  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // @Override
  // public AbstractCollectionValidatorBuilder<E, List<E>, PropertyBuilder<ListProperty<E>>, ?> withValdidator() {
  //
  // Function factory = new Function<PropertyBuilder<ListProperty<E>>, ValidatorBuilderCollection<E,
  // PropertyBuilder<ListProperty<E>>>>() {
  //
  // @Override
  // public ValidatorBuilderCollection<E, PropertyBuilder<ListProperty<E>>> apply(PropertyBuilder<ListProperty<E>> t) {
  //
  // return new ValidatorBuilderCollection<>(t);
  // }
  // };
  // return (ValidatorBuilderCollection) withValdidator(factory);
  // }

}
