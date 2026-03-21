/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

/**
 * {@link PropertyMetadata#getLock() Lock} that can make the owning {@link Property} {@link #isReadOnly() read-only}.
 * Typically this is the owning bean.
 *
 * @see PropertyMetadata#getLock()
 * @since 1.0.0
 */
public interface PropertyLock extends AttributeReadOnly {

  /**
   * <b>ATTENTION</b>: This is an internal method. Never use it from outside.
   *
   * @return a new {@link PropertyLock} for a {@link WritableProperty#getReadOnly() read-only property}.
   */
  PropertyLock createReadOnlyLock();

}
