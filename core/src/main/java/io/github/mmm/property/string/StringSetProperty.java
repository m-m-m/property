/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.github.mmm.property.PropertyMetadata;

/**
 * {@link StringCollectionProperty} maintaining a {@link Set} of {@link String}s. It will preserve the order of its
 * elements as they are {@link #add(String) added} in its {@link #getValue() value}. However, the order of the
 * {@link #getAsSet() value as set} is unspecified.<br>
 * A {@link StringSetProperty} is especially useful to represent tags, synonyms/aliases, etc. However, study
 * {@link StringCollectionProperty} before using.
 *
 * @since 1.0.0
 */
public class StringSetProperty extends StringCollectionProperty {

  private final Set<String> set;

  private final Set<String> immutableSet;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringSetProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringSetProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
    this.set = new HashSet<>();
    this.immutableSet = Collections.unmodifiableSet(this.set);
  }

  @Override
  protected Collection<String> getCollection() {

    return this.set;
  }

  /**
   * @return an {@link Collections#unmodifiableSet(Set) unmodifiable} {@link Set} with the elements from the
   *         {@link #get() value}.
   */
  public Set<String> getAsSet() {

    return this.immutableSet;
  }

}
