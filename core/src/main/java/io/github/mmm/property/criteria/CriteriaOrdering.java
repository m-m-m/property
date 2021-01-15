/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.property.ReadableProperty;

/**
 *
 */
public class CriteriaOrdering {

  private final ReadableProperty<?> property;

  private final SortOrder order;

  /**
   * The constructor.
   *
   * @param property the {@link ReadableProperty} to order by.
   * @param order the {@link SortOrder} to order by.
   */
  public CriteriaOrdering(ReadableProperty<?> property, SortOrder order) {

    super();
    this.property = property;
    this.order = order;
  }

  /**
   * @return property
   */
  public ReadableProperty<?> getProperty() {

    return this.property;
  }

  /**
   * @return order
   */
  public SortOrder getOrder() {

    return this.order;
  }

  @Override
  public String toString() {

    return this.property.path() + " " + this.order;
  }

}
