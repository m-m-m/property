/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.container;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataNone;
import io.github.mmm.property.PropertyMetadataType;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.booleans.ReadableBooleanProperty;
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
  public ContainerProperty(String name, Property<E> valueProperty) {

    this(name, valueProperty, PropertyMetadataNone.get());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueProperty the {@link #getValueProperty() value property}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  @SuppressWarnings("unchecked")
  public ContainerProperty(String name, Property<E> valueProperty, PropertyMetadata<V> metadata) {

    super(name, metadata);
    if (valueProperty == null) {
      valueProperty = (Property<E>) metadata.get(METADATA_KEY_COMPONENT_PROPERTY);
    }
    this.valueProperty = valueProperty;
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
  public Property<E> getValueProperty() {

    return this.valueProperty;
  }

  @Override
  public ReadableIntegerProperty sizeProperty() {

    if (this.sizeProperty == null) {
      getChangeAwareValue();
      this.sizeProperty = new IntegerProperty(getName() + ".size",
          new PropertyMetadataType<>(() -> Integer.valueOf(size())));
    }
    return this.sizeProperty;
  }

  @Override
  public ReadableBooleanProperty emptyProperty() {

    if (this.emptyProperty == null) {
      getChangeAwareValue();
      this.emptyProperty = new BooleanProperty(getName() + ".empty",
          new PropertyMetadataType<>(() -> Boolean.valueOf(isEmpty())));
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

}
