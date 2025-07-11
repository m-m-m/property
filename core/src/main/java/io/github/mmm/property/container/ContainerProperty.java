/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container;

import java.util.function.Supplier;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.booleans.ReadableBooleanProperty;
import io.github.mmm.property.impl.metadata.PropertyMetadataExpression;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.property.number.integers.ReadableIntegerProperty;

/**
 * Implementation of {@link WritableContainerProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getValueProperty() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public abstract class ContainerProperty<V, E> extends Property<V> implements WritableContainerProperty<V, E> {

  /** @see #getValueProperty() */
  protected final Property<E> valueProperty;

  private IntegerProperty sizeProperty;

  private BooleanProperty emptyProperty;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   */
  public ContainerProperty(String name, WritableProperty<E> valueProperty) {

    this(name, valueProperty, PropertyMetadataNone.get());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ContainerProperty(String name, WritableProperty<E> valueProperty, PropertyMetadata<V> metadata) {

    super(name, metadata);
    this.valueProperty = (Property<E>) valueProperty;
  }

  @Override
  public boolean isValueMutable() {

    return true;
  }

  @Override
  protected boolean isValueEqual(V newValue, V oldValue) {

    return (newValue == oldValue);
  }

  @Override
  public WritableProperty<E> getValueProperty() {

    return this.valueProperty;
  }

  @Override
  public ReadableIntegerProperty sizeProperty() {

    if (this.sizeProperty == null) {
      getChangeAwareValue();
      this.sizeProperty = new IntegerProperty(getName() + ".size",
          new PropertyMetadataExpression<>(() -> Integer.valueOf(size())));
    }
    return this.sizeProperty;
  }

  @Override
  public ReadableBooleanProperty emptyProperty() {

    if (this.emptyProperty == null) {
      getChangeAwareValue();
      this.emptyProperty = new BooleanProperty(getName() + ".empty",
          new PropertyMetadataExpression<>(() -> Boolean.valueOf(isEmpty())));
    }
    return this.emptyProperty;
  }

  /**
   * Invalidates internal properties such as {@link #sizeProperty()} and {@link #emptyProperty()}.
   */
  protected void invalidateProperties() {

    fireEventFor(this.sizeProperty);
    fireEventFor(this.emptyProperty);
  }

  @Override
  protected abstract Supplier<? extends V> createReadOnlyExpression();

}
