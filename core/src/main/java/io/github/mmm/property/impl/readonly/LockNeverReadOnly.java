/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.readonly;

import io.github.mmm.property.PropertyLock;

/**
 * Implementation of {@link PropertyLock} that is never {@link #isReadOnly() read-only}.
 */
public final class LockNeverReadOnly implements PropertyLock {

  /** The singleton instance. */
  public static final LockNeverReadOnly INSTANCE = new LockNeverReadOnly();

  @Override
  public boolean isReadOnly() {

    return false;
  }

  @Override
  public PropertyLock createReadOnlyLock() {

    return LockAlwaysReadOnly.INSTANCE;
  }

}
