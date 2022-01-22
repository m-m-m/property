/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;

import io.github.mmm.base.exception.ObjectNotFoundException;
import io.github.mmm.property.criteria.impl.ConjunctionPredicate;
import io.github.mmm.property.criteria.impl.SimplePredicate;
import io.github.mmm.value.CriteriaObject;

/**
 * {@link CriteriaOperator} for a {@link CriteriaPredicate} that always evaluates to a {@link Boolean} value.
 *
 * @since 1.0.0
 */
public class PredicateOperator extends CriteriaOperator {

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal =}
   * ({@link Object#equals(Object) equal})</em> to {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator EQ = new PredicateOperator("=", PRIO_4_COMP, "EQ");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal <>} (not
   * {@link Object#equals(Object) equal})</em> to {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator NEQ = new PredicateOperator("<>", EQ, "NEQ");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal <} (less than)</em>
   * {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator LT = new PredicateOperator("<", PRIO_4_COMP, "LT");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal <=} (less or
   * equal)</em> {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator LE = new PredicateOperator("<=", PRIO_4_COMP, "LE");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal >} (greater
   * than)</em> {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator GT = new PredicateOperator(">", false, LE, "GT");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>{@literal >=} (greater or
   * equal)</em> {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator GE = new PredicateOperator(">=", false, LT, "GE");

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} <em>IS NULL</em>.
   */
  public static final PredicateOperator IS_NULL = new PredicateOperator("IS NULL", true, PRIO_4_COMP);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} <em>IS NOT NULL</em>.
   */
  public static final PredicateOperator IS_NOT_NULL = new PredicateOperator("IS NOT NULL", IS_NULL);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>LIKE</em>
   * {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator LIKE = new PredicateOperator("LIKE", PRIO_7_OR);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>NOT LIKE</em>
   * {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator NOT_LIKE = new PredicateOperator("NOT LIKE", LIKE);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>IN</em>
   * {@link CriteriaExpression#getSecondArg() second argument} (list).
   */
  public static final PredicateOperator IN = new PredicateOperator("IN", PRIO_7_OR);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} is <em>NOT IN</em>
   * {@link CriteriaExpression#getSecondArg() second argument} (list).
   */
  public static final PredicateOperator NOT_IN = new PredicateOperator("NOT IN", IN);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} (collection) <em>CONTAINS</em> the
   * {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator CONTAINS = new PredicateOperator("CONTAINS", PRIO_7_OR);

  /**
   * Operator to check if {@link CriteriaExpression#getFirstArg() first argument} (collection) <em>NOT CONTAINS</em> the
   * {@link CriteriaExpression#getSecondArg() second argument}.
   */
  public static final PredicateOperator NOT_CONTAINS = new PredicateOperator("NOT CONTAINS", CONTAINS);

  /** Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>AND</em>. */
  public static final PredicateOperator AND = new PredicateOperator("AND", PRIO_6_AND);

  /**
   * Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>NAND</em>. Typically normalized
   * as {@code NOT(arg1 AND arg2 ...)}.
   */
  public static final PredicateOperator NAND = new PredicateOperator("NAND", AND);

  /** Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>OR</em>. */
  public static final PredicateOperator OR = new PredicateOperator("OR", PRIO_7_OR);

  /**
   * Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>NOR</em>. Typically normalized
   * as {@code NOT(arg1 OR arg2 ...)}.
   */
  public static final PredicateOperator NOR = new PredicateOperator("NOR", OR);

  /** Operator to negate a single {@link CriteriaExpression#getArgs() argument} (<em>NOT</em>). */
  public static final PredicateOperator NOT = new PredicateOperator("NOT", true, null, true, PRIO_5_NOT);

  private final boolean unary;

  private final int priority;

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param priority the {@link #getPriority() priority}.
   */
  protected PredicateOperator(String syntax, int priority) {

    this(syntax, priority, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param priority the {@link #getPriority() priority}.
   * @param name the {@link #getName() name}.
   */
  protected PredicateOperator(String syntax, int priority, String name) {

    this(syntax, false, null, false, priority, name);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   * @param not the {@link #not() negated} form or {@code null}.
   */
  protected PredicateOperator(String syntax, PredicateOperator not) {

    this(syntax, not, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param name the {@link #getName() name}.
   */
  protected PredicateOperator(String syntax, PredicateOperator not, String name) {

    this(syntax, true, not, name);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param unary the {@link #isUnary() unary flag}.
   * @param priority the {@link #getPriority() priority}.
   */
  protected PredicateOperator(String syntax, boolean unary, int priority) {

    this(syntax, false, null, unary, priority);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   */
  protected PredicateOperator(String syntax, boolean inverse, PredicateOperator not) {

    this(syntax, inverse, not, not.unary, not.priority);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   * @param name the {@link #getName() name}.
   */
  protected PredicateOperator(String syntax, boolean inverse, PredicateOperator not, String name) {

    this(syntax, inverse, not, not.unary, not.priority, name);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   * @param priority the {@link #getPriority() priority}.
   * @param unary the {@link #isUnary() unary flag}.
   */
  protected PredicateOperator(String syntax, boolean inverse, PredicateOperator not, boolean unary, int priority) {

    this(syntax, inverse, not, unary, priority, null);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   * @param unary the {@link #isUnary() unary flag}.
   * @param priority the {@link #getPriority() priority}.
   * @param name the {@link #getName() name}.
   */
  protected PredicateOperator(String syntax, boolean inverse, PredicateOperator not, boolean unary, int priority,
      String name) {

    super(syntax, not, inverse, name);
    this.unary = unary;
    this.priority = priority;
  }

  @Override
  public PredicateOperator not() {

    return (PredicateOperator) super.not();
  }

  @Override
  public boolean isConjunction() {

    if (this == AND) {
      return true;
    } else if (this == OR) {
      return true;
    } else if (this == NAND) {
      return true;
    } else if (this == NOR) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isUnary() {

    return this.unary;
  }

  @Override
  public int getPriority() {

    return this.priority;
  }

  @Override
  public boolean isInfix() {

    if (isNullBased(this)) {
      return true;
    }
    return super.isInfix();
  }

  @Override
  public CriteriaPredicate expression(List<CriteriaObject<?>> args) {

    if ((args == null) || args.isEmpty()) {
      throw new ObjectNotFoundException("Arguments");
    }
    if (isConjunction()) {
      BooleanSelection[] predicates = new BooleanSelection[args.size()];
      int i = 0;
      try {
        for (CriteriaObject<?> arg : args) {
          predicates[i] = (BooleanSelection) arg;
          i++;
        }
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Argument at index " + i + " is not a BooleanSupplier (predicate).", e);
      }
      return new ConjunctionPredicate(this, predicates);
    } else {
      int argCount = args.size();
      if (argCount <= 2) {
        CriteriaObject<?> arg1 = args.get(0);
        CriteriaObject<?> arg2 = null;
        if (argCount > 1) {
          arg2 = args.get(1);
        }
        if ((this == NOT) == (argCount == 1)) {
          return new SimplePredicate(arg1, this, arg2);
        }
      }
      throw new IllegalArgumentException("Operator '" + this + "' does not accept " + argCount + " arguments");
    }
  }

  /**
   * @param syntax the {@link #getSyntax() syntax} of the requested {@link PredicateOperator}.
   * @return the predefined {@link PredicateOperator} or {@code null} if no such operator exists.
   */
  public static PredicateOperator of(String syntax) {

    CriteriaOperator op = CriteriaOperator.of(syntax);
    if (op instanceof PredicateOperator) {
      return (PredicateOperator) op;
    }
    return null;
  }

  /**
   * @param op the {@link CriteriaOperator} to check.
   * @return {@code true} if {@link #LIKE} or {@link #NOT_LIKE}, {@code false} otherwise.
   */
  public static boolean isLikeBased(CriteriaOperator op) {

    return ((op == LIKE) || (op == NOT_LIKE));
  }

  /**
   * @param op the {@link CriteriaOperator} to check.
   * @return {@code true} if {@link #IS_NULL} or {@link #IS_NOT_NULL}, {@code false} otherwise.
   */
  public static boolean isNullBased(CriteriaOperator op) {

    return ((op == IS_NULL) || (op == IS_NOT_NULL));
  }

  /**
   * Ensure class-loading and initialization.
   */
  static void load() {

  }

}
