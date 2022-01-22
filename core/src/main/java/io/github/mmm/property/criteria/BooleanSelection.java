/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.function.Supplier;

import io.github.mmm.value.CriteriaObject;

/**
 * {@link Supplier} bound to {@link Boolean}.
 *
 * @since 1.0.0
 * @see BooleanLiteral
 * @see CriteriaPredicate
 */
public interface BooleanSelection extends CriteriaObject<Boolean> {

  /**
   * @return a simplified form of this {@link BooleanSelection} or this instance itself if already simplified.
   * @see CriteriaExpression#simplify()
   */
  BooleanSelection simplify();

  /**
   * @return the negated form of this {@link BooleanSelection} as predicate.
   * @see CriteriaPredicate#not()
   */
  BooleanSelection not();

}
