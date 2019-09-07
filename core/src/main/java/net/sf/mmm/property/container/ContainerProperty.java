/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.container;

import java.lang.reflect.Type;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.PropertyMetadataNone;
import net.sf.mmm.property.PropertyMetadataType;
import net.sf.mmm.property.booleans.BooleanProperty;
import net.sf.mmm.property.booleans.ReadableBooleanProperty;
import net.sf.mmm.property.number.integers.IntegerProperty;
import net.sf.mmm.property.number.integers.ReadableIntegerProperty;

/**
 * Implementation of {@link WritableContainerProperty}.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @param <E> type of {@link #getComponentClass() elements} contained in the {@link #getValue() value}.
 *
 * @since 1.0.0
 */
public abstract class ContainerProperty<V, E> extends Property<V> implements WritableContainerProperty<V, E> {

  private Class<E> componentClass;

  private Type componentType;

  private IntegerProperty sizeProperty;

  private BooleanProperty emptyProperty;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public ContainerProperty(String name, Class<E> componentClass, Type componentType) {

    this(name, componentClass, componentType, PropertyMetadataNone.getInstance());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ContainerProperty(String name, Class<E> componentClass, Type componentType, PropertyMetadata<V> metadata) {

    super(name, metadata);
    this.componentClass = componentClass;
    this.componentType = componentType;
  }

  @Override
  public Class<E> getComponentClass() {

    return this.componentClass;
  }

  @Override
  public Type getComponentType() {

    return this.componentType;
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
