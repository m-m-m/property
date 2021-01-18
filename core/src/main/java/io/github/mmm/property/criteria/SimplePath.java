/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.value.PropertyPath;

/**
 * Implementation of {@link PropertyPath} for a plain path without {@link #get() value}. Will e.g. be used after
 * unamrshalling a {@link io.github.mmm.property.Property} from a {@link CriteriaExpression}
 * {@link CriteriaExpression#getArgs() argument}.
 *
 * @since 1.0.0
 */
public final class SimplePath implements PropertyPath<Object> {

  /** Name of the {@link #path() path} property for marshalling. */
  public static final String NAME_PATH = "path";

  private final String path;

  /**
   * The constructor.
   *
   * @param path the {@link #getName() path/name}.
   */
  public SimplePath(String path) {

    super();
    this.path = path;
  }

  @Override
  public Object get() {

    return null;
  }

  @Override
  public Object getSafe() {

    return null;
  }

  @Override
  public String getName() {

    return this.path;
  }

  @Override
  public String toString() {

    return this.path;
  }

}
