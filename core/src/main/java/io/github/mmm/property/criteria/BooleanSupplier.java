/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.function.Supplier;

/**
 * {@link Supplier} bound to {@link Boolean}.
 *
 * @since 1.0.0
 * @see BooleanLiteral
 * @see CriteriaPredicate
 */
public interface BooleanSupplier extends Supplier<Boolean> {

  /**
   * @return a simplified form of this {@link BooleanSupplier} or this instance itself if already simplified.
   * @see CriteriaExpression#simplify()
   */
  BooleanSupplier simplify();

  /**
   * @return the negated form of this {@link BooleanSupplier} as predicate.
   * @see CriteriaPredicate#not()
   */
  BooleanSupplier not();

}
