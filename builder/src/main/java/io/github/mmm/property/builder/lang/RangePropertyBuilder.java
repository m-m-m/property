/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.base.range.Range;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.builder.PropertyBuilders;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.property.range.RangeProperty;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.validation.range.ValidatorBuilderRange;

/**
 * {@link PropertyBuilder} for {@link StringProperty}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @since 1.0.0
 */
public final class RangePropertyBuilder<V> extends
    PropertyBuilder<Range<V>, RangeProperty<V>, ValidatorBuilderRange<V, RangePropertyBuilder<V>>, RangePropertyBuilder<V>> {

  private SimpleProperty<V> valueProperty;

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public RangePropertyBuilder(PropertyBuilders parent) {

    this(parent, null);
  }

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   * @param valueProperty the {@link RangeProperty#getValueProperty() value property}.
   */
  public RangePropertyBuilder(PropertyBuilders parent, SimpleProperty<V> valueProperty) {

    super(parent);
    this.valueProperty = valueProperty;
  }

  /**
   * @param property the {@link RangeProperty#getValueProperty() value property}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public RangePropertyBuilder<V> valueProperty(SimpleProperty<V> property) {

    this.valueProperty = property;
    return this;
  }

  @Override
  protected ValidatorBuilderRange<V, RangePropertyBuilder<V>> createValidatorBuilder() {

    return new ValidatorBuilderRange<>(this);
  }

  @Override
  protected RangeProperty<V> build(String name, PropertyMetadata<Range<V>> metadata) {

    return new RangeProperty<>(name, this.valueProperty, metadata);
  }

}
