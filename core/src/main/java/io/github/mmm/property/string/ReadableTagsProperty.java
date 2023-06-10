/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Set;

/**
 * {@link ReadableStringProperty} with {@link #getTags() tags}.
 *
 * @since 1.0.0
 */
public interface ReadableTagsProperty extends ReadableStringProperty {

  /**
   * @return the {@link java.util.Collections#unmodifiableSet(Set) immutable} {@link Set} of individual tags.
   */
  Set<String> getTags();

}
