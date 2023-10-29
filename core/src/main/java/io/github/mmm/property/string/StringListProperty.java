/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.property.PropertyMetadata;

/**
 * {@link StringCollectionProperty} maintaining a {@link List} of {@link String}s.
 *
 * @since 1.0.0
 */
public class StringListProperty extends StringCollectionProperty {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringListProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringListProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
  }

  /**
   * @return a new {@link List} with the elements from the {@link #get() value}. Will be computed every time this method
   *         gets called so avoid sub-sequent calls.
   */
  public List<String> getAsList() {

    return collect(new ArrayList<>());
  }

}
