/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.container;

import java.util.List;
import java.util.Set;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.container.list.ListProperty;
import io.github.mmm.property.container.set.SetProperty;
import io.github.mmm.validation.collection.ValidatorBuilderSet;

/**
 * {@link ProcessBuilder} for {@link ListProperty}.
 *
 * @param <E> the generic type of the {@link List#contains(Object) elements contained} in the {@link List}.
 * @since 1.0.0
 * @see PropertyBuilder#asSet()
 */
public class SetPropertyBuilder<E> extends
    PropertyBuilder<Set<E>, SetProperty<E>, ValidatorBuilderSet<E, SetPropertyBuilder<E>>, SetPropertyBuilder<E>> {

  private Property<E> valueProperty;

  /**
   * The constructor.
   */
  public SetPropertyBuilder() {

    super();
  }

  /**
   * The constructor.
   *
   * @param componentProperty the {@link ListProperty#getValueProperty() value property}.
   */
  public SetPropertyBuilder(Property<E> componentProperty) {

    super();
    this.valueProperty = componentProperty;
  }

  /**
   * @param property the {@link ListProperty#getValueProperty() value property}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SetPropertyBuilder<E> componentProperty(Property<E> property) {

    this.valueProperty = property;
    return this;
  }

  @Override
  protected ValidatorBuilderSet<E, SetPropertyBuilder<E>> createValidatorBuilder() {

    return new ValidatorBuilderSet<>(this);
  }

  @Override
  protected SetProperty<E> build(String name, PropertyMetadata<Set<E>> metadata) {

    return new SetProperty<>(name, this.valueProperty, metadata);
  }

}
