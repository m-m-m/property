/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import io.github.mmm.property.Property;
import io.github.mmm.property.builder.PropertyBuilder;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * {@link PropertyBuilder} for {@link IntegerProperty}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 * @param <P> type of the {@link Property} to build.
 * @param <B> type of the {@link ObjectValidatorBuilder validator builder} for {@link #withValidator()}.
 * @param <SELF> type of this {@link ComparablePropertyBuilder} itself.
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public abstract class ComparablePropertyBuilder<V extends Comparable, P extends Property<V>, B extends ObjectValidatorBuilder<V, ? extends ComparablePropertyBuilder<V, P, B, SELF>, ?>, SELF extends ComparablePropertyBuilder<V, P, B, SELF>>
    extends PropertyBuilder<V, P, B, SELF> {

  /**
   * The constructor.
   */
  public ComparablePropertyBuilder() {

    super();
  }

  /**
   * @return a {@link RangePropertyBuilder} using {@link #build(String) this property configuration} for its bounds (min
   *         and max).
   */
  @SuppressWarnings("unchecked")
  public RangePropertyBuilder<V> asRange() {

    return builder(new RangePropertyBuilder<>((SimpleProperty<V>) build("Value", true)));
  }

}
