/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.readonly;

import io.github.mmm.property.PropertyLock;

/**
 * Implementation of {@link PropertyLock} that is always {@link #isReadOnly() read-only}.
 *
 * @since 1.0.0
 */
public final class LockAlwaysReadOnly implements PropertyLock {

  /** The singleton instance. */
  public static final LockAlwaysReadOnly INSTANCE = new LockAlwaysReadOnly();

  @Override
  public boolean isReadOnly() {

    return true;
  }

  @Override
  public PropertyLock createReadOnlyLock() {

    return this;
  }

}
