/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.mmm.value.CriteriaObject;

/**
 * Operator for {@link CriteriaExpression} to compute the result from the {@link CriteriaExpression#getArgs()
 * arguments}. <br>
 * This class is designed as a dynamic and polymorphic {@link Enum}. There are sub-classes like e.g.
 * {@link PredicateOperator} that define concrete constants to be used as {@link CriteriaOperator} instance. for some
 * exotic reason you need to add an additional operation you may subclass {@link CriteriaOperator} and define additional
 * constants. Those will also be supported by {@link #of(String)} method. Please ensure to never create duplicated
 * {@link CriteriaOperator}s (with the {@link #getSyntax() name} of an existing one).
 *
 * @since 1.0.0
 * @see CriteriaExpression#getOperator()
 */
public abstract class CriteriaOperator {

  /** {@link #getPriority() Priority} for bitwise NOT operation. */
  protected static final int PRIO_1_TILDE = 1;

  /** {@link #getPriority() Priority} for multiplication, division, or modulo operation. */
  protected static final int PRIO_2_MUL = 2;

  /** {@link #getPriority() Priority} for operations like addition, subtraction, conjunction, etc. */
  protected static final int PRIO_3_ADD = 3;

  /** {@link #getPriority() Priority} for comparison operations (eq, neq, lt, etc.). */
  protected static final int PRIO_4_COMP = 4;

  /** {@link #getPriority() Priority} for NOT operation. */
  protected static final int PRIO_5_NOT = 5;

  /** {@link #getPriority() Priority} for AND operation. */
  protected static final int PRIO_6_AND = 6;

  /** {@link #getPriority() Priority} for operations like OR, IN, LIKE, BETWEEN, etc. */
  protected static final int PRIO_7_OR = 7;

  /** {@link #getPriority() Priority} for assignment operation (=). */
  protected static final int PRIO_8_ASSIGN = 8;

  private static final Map<String, CriteriaOperator> SYNTAX2OPERATOR_MAP = new HashMap<>();

  private final String syntax;

  private final String name;

  private final boolean inverse;

  private CriteriaOperator not;

  static {
    PredicateOperator.load();
    CriteriaAggregationOperator.load();
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   */
  protected CriteriaOperator(String syntax) {

    this(syntax, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   */
  protected CriteriaOperator(String syntax, CriteriaOperator not) {

    this(syntax, not, (not != null));
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   */
  protected CriteriaOperator(String syntax, CriteriaOperator not, boolean inverse) {

    this(syntax, not, inverse, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   * @param name the {@link #getName() name}.
   */
  protected CriteriaOperator(String syntax, CriteriaOperator not, boolean inverse, String name) {

    super();
    assert ((syntax != null) && !syntax.isEmpty()) : syntax;
    this.syntax = syntax;
    if (name == null) {
      this.name = syntax.replace(' ', '_');
    } else {
      this.name = name;
    }
    assert isValidName() : this.name;
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
    CriteriaOperator duplicate = SYNTAX2OPERATOR_MAP.putIfAbsent(syntax, this);
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
   * @return {@code true} if this is a unary {@link CriteriaOperator} that can take only a
   *         {@link CriteriaExpression#getFirstArg() single argument}, {@code false} otherwise.
   */
  public abstract boolean isUnary();

  /**
   * @return {@code true} if one of the conjunctions {@link PredicateOperator#AND AND}, {@link PredicateOperator#OR OR},
   *         {@link PredicateOperator#NAND NAND}, or {@link PredicateOperator#NOR NOR}. Otherwise {@code false} is
   *         returned.
   */
  public boolean isConjunction() {

    return false;
  }

  /**
   * @return the negated form of this operator (e.g. "not equal" for "equal" or "greater-or-equal" for "less-than"). May
   *         be {@code null} if no negated form exists.
   */
  public CriteriaOperator not() {

    return this.not;
  }

  /**
   * @return the syntax of this operator. This is typically what you would use in SQL. You may need to map it to the
   *         concrete syntax of your database.
   */
  public String getSyntax() {

    return this.syntax;
  }

  /**
   * @return a {@link #toString() string representation} like {@link #getSyntax() syntax} but guaranteed to match
   *         {@code [a-Z0-9_]+}.
   */
  public String getName() {

    return this.name;
  }

  private boolean isValidName() {

    int length = this.name.length();
    if (length == 0) {
      return false;
    }
    for (int i = length - 1; i >= 0; i--) {
      char c = this.name.charAt(i);
      boolean valid = ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || ((c >= '0') && (c <= '9'))
          || (c == '_');
      if (!valid) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return {@code true} if this operator should be placed as infix between the arguments (e.g. {@code 1+2+3} or
   *         {@code age>=18}), {@code false} if used as prefix (e.g. {@code NOT flag} or {@code SUM(e.Price)}).
   */
  public boolean isInfix() {

    return !isUnary();
  }

  /**
   * @return the priority of this operator. In case you have an expression "A op1 B op2 C" then if the operator priority
   *         of "op2" is higher it means "A op1 (B op2 C)" and otherwise "(A op1 B) op2 C".
   */
  public abstract int getPriority();

  /**
   * @param args the {@link CriteriaExpression#getArgs() arguments}.
   * @return the {@link CriteriaExpression}.
   */
  public abstract CriteriaExpression<?> expression(List<CriteriaObject<?>> args);

  @Override
  public String toString() {

    return this.syntax;
  }

  /**
   * This method is used to unmarshall an {@link CriteriaOperator} from its {@link #getSyntax() syntax}.
   *
   * @param syntax the {@link #getSyntax() syntax} of the requested {@link CriteriaOperator}.
   * @return the predefined {@link CriteriaOperator} or {@code null} if no such {@link CriteriaOperator} exists.
   */
  public static CriteriaOperator of(String syntax) {

    return SYNTAX2OPERATOR_MAP.get(syntax);
  }

}
