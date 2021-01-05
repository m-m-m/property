/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.impl.readonly;

import io.github.mmm.property.AttributeReadOnly;

/**
 * Implementation of {@link AttributeReadOnly} that is always read-only and always returns {@code true}.
 */
public final class AttributeAlwaysReadOnly implements AttributeReadOnly {

  /** The singleton instance. */
  public static final AttributeAlwaysReadOnly INSTANCE = new AttributeAlwaysReadOnly();

  @Override
  public boolean isReadOnly() {

    return true;
  }

}
