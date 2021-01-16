/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria.impl;

import io.github.mmm.property.criteria.CriteriaExpression;
import io.github.mmm.property.criteria.CriteriaSqlFormatter;

/**
 * Base-class for a search criteria.
 *
 * @param <R> type of the value to compare.
 * @since 1.0.0
 */
public abstract class AbstractCriteriaExpression<R> implements CriteriaExpression<R> {

  /**
   * The constructor.
   */
  public AbstractCriteriaExpression() {

    super();
  }

  @Override
  public String toString() {

    return new CriteriaSqlFormatter().onExpression(this).toString();
  }

}
