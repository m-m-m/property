/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.container;

import java.util.List;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.container.list.ListProperty;
import io.github.mmm.validation.collection.ValidatorBuilderList;

/**
 * {@link ProcessBuilder} for {@link ListProperty}.
 *
 * @param <E> the generic type of the {@link List#contains(Object) elements contained} in the {@link List}.
 * @since 1.0.0
 * @see PropertyBuilder#asList()
 */
public class ListPropertyBuilder<E> extends
    PropertyBuilder<List<E>, ListProperty<E>, ValidatorBuilderList<E, ListPropertyBuilder<E>>, ListPropertyBuilder<E>> {

  private Property<E> valueProperty;

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public ListPropertyBuilder(PropertyBuilders parent) {

    this(parent, null);
  }

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   * @param valueProperty the {@link ListProperty#getValueProperty() value property}.
   */
  public ListPropertyBuilder(PropertyBuilders parent, Property<E> valueProperty) {

    super(parent);
    this.valueProperty = valueProperty;
  }

  /**
   * @param property the {@link ListProperty#getValueProperty() value property}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public ListPropertyBuilder<E> valueProperty(Property<E> property) {

    this.valueProperty = property;
    return this;
  }

  @Override
  protected ValidatorBuilderList<E, ListPropertyBuilder<E>> createValidatorBuilder() {

    return new ValidatorBuilderList<>(this);
  }

  @Override
  protected ListProperty<E> build(String name, PropertyMetadata<List<E>> metadata) {

    return new ListProperty<>(name, this.valueProperty, metadata);
  }

}
