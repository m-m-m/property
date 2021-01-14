/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.HashMap;
import java.util.Map;

/**
 * Operator for {@link CriteriaExpression} to compute the result from the {@link CriteriaExpression#getArgs()
 * arguments}. <br>
 * This class is designed as a dynamic and polymorphic {@link Enum}. There are sub-classes like e.g.
 * {@link PredicateOperator} that define concrete constants to be used as {@link Operator} instance. for some exotic
 * reason you need to add an additional operation you may subclass {@link Operator} and define additional constants.
 * Those will also be supported by {@link #of(String)} method. Please ensure to never create duplicated
 * {@link Operator}s (with the {@link #getSyntax() name} of an existing one).
 *
 * @since 1.0.0
 * @see CriteriaExpression#getOperator()
 */
public abstract class Operator {

  private static final Map<String, Operator> SYNTAX2OPERATOR_MAP = new HashMap<>();

  private final String syntax;

  private final boolean inverse;

  private Operator not;

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   */
  protected Operator(String syntax) {

    this(syntax, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   * @param not the {@link #not() negated} form or {@code null}.
   */
  protected Operator(String syntax, Operator not) {

    this(syntax, not, (not != null));
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   */
  protected Operator(String syntax, Operator not, boolean inverse) {

    super();
    this.syntax = syntax;
    this.inverse = inverse;
    if (not != null) {
      this.not = not;
      if (not.not != this) {
        if (not.not != null) {
          throw new IllegalStateException(
              "Negation of '" + syntax + "' cannot be '" + not + "' as this already has negation '" + not.not + "'!");
        }
        not.not = this;
      }
    }
    Operator duplicate = SYNTAX2OPERATOR_MAP.putIfAbsent(syntax, this);
    assert (duplicate == null);
  }

  /**
   * @return {@code true} if this is the {@link #not() negated} variant of a regular form, {@code false} otherwise. So
   *         the "like" operator would return {@code false} while "not like" operator will return {@code true}.
   */
  public boolean isInverse() {

    return this.inverse;
  }

  /**
   * @return the negated form of this operator (e.g. "not equal" for "equal" or "greater-or-equal" for "less-than"). May
   *         be {@code null} if no negated form exists.
   */
  public Operator not() {

    return this.not;
  }

  /**
   * @return the syntax of this operator. This is typically what you would use in SQL. You may need to map it to the
   *         concrete syntax of your database.
   */
  public String getSyntax() {

    return this.syntax;
  }

  @Override
  public String toString() {

    return this.syntax;
  }

  /**
   * This method is used to unmarshall an {@link Operator} from its {@link #getSyntax() syntax}.
   *
   * @param syntax the {@link #getSyntax() syntax} of the requested {@link Operator}.
   * @return the predefined {@link Operator} or {@code null} if no such {@link Operator} exists.
   */
  public static Operator of(String syntax) {

    return SYNTAX2OPERATOR_MAP.get(syntax);
  }

}
