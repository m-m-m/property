/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

/**
 * Interface for an object that can be {@link #isReadOnly() read-only} (or mutable).
 *
 * @since 1.0.0
 */
public interface AttributeReadOnly {

  /**
   * @return {@code true} if this object is immutable (read-only) and attempts to modify it will fail with an exception,
   *         {@code false} otherwise.
   * @see WritableProperty#getReadOnly()
   */
  boolean isReadOnly();

}
