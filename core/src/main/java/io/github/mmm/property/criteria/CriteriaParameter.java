/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

/**
 * Interface for a parameter (bind variable) of a database statement.
 *
 * @param <V> type of the {@link #getValue() value}.
 *
 * @see CriteriaParameters#iterator()
 */
public interface CriteriaParameter<V> {

  /**
   * @return the zero-based index of this parameter. <b>ATTENTION</b>: in JDBC index of parameters starts with {@code 1}
   *         (and not with {@code 0}) so you need to add {@code 1} for JDBC.
   */
  int getIndex();

  /**
   * @return the name of the parameter or {@code null} for an {@link #getIndex() indexed parameter}.
   */
  default String getName() {

    return null;
  }

  /**
   * @return the parameter value. May be {@code null}.
   */
  V getValue();

}
