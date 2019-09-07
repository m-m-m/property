/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.number;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;

/**
 * Implementation of {@link WritableNumberProperty}.
 *
 * @param <N> type of the numeric {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class NumberProperty<N extends Number & Comparable<? super N>> extends Property<N>
    implements WritableNumberProperty<N> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public NumberProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public NumberProperty(String name, PropertyMetadata<N> metadata) {

    super(name, metadata);
  }

}
