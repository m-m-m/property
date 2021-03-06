/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.value.PropertyPath;

/**
 * An ordering for an {@code ORDER BY} clause.
 *
 * @since 1.0.0
 */
public class CriteriaOrdering {

  private final PropertyPath<?> property;

  private final SortOrder order;

  /**
   * The constructor.
   *
   * @param path the {@link PropertyPath} to order by.
   * @param order the {@link SortOrder} to order by.
   */
  public CriteriaOrdering(PropertyPath<?> path, SortOrder order) {

    super();
    this.property = path;
    this.order = order;
  }

  /**
   * @return the {@link PropertyPath property} to order.
   */
  public PropertyPath<?> getProperty() {

    return this.property;
  }

  /**
   * @return the {@link SortOrder} to order by.
   */
  public SortOrder getOrder() {

    return this.order;
  }

  @Override
  public String toString() {

    return this.property.path() + " " + this.order;
  }

}
