/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.readonly;

import io.github.mmm.property.AttributeReadOnly;

/**
 * Implementation of {@link AttributeReadOnly} that is never read-only and always returns {@code false}.
 */
public final class AttributeNeverReadOnly implements AttributeReadOnly {

  /** The singleton instance. */
  public static final AttributeNeverReadOnly INSTANCE = new AttributeNeverReadOnly();

  @Override
  public boolean isReadOnly() {

    return false;
  }

}
