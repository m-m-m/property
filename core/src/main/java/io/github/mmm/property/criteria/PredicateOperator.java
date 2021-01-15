/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.base.exception.ObjectNotFoundException;

/**
 * {@link Operator} for a {@link CriteriaPredicate} that always evaluates to a {@link Boolean} value.
 *
 * @since 1.0.0
 */
public class PredicateOperator extends Operator {

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal =}
   * ({@link Object#equals(Object) equal})</em> to {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator EQ = new PredicateOperator("=");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal <>} (not
   * {@link Object#equals(Object) equal})</em> to {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator NEQ = new PredicateOperator("<>", EQ);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal <} (less than)</em>
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator LT = new PredicateOperator("<");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal <=} (less or equal)</em>
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator LE = new PredicateOperator("<=");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal >} (greater than)</em>
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator GT = new PredicateOperator(">", LE, false);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>{@literal >=} (greater or
   * equal)</em> {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator GE = new PredicateOperator(">=", LT, false);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} <em>IS NULL</em>.
   */
  public static final PredicateOperator IS_NULL = new PredicateOperator("IS NULL");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} <em>IS NOT NULL</em>.
   */
  public static final PredicateOperator IS_NOT_NULL = new PredicateOperator("IS NOT NULL", IS_NULL);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>LIKE</em>
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator LIKE = new PredicateOperator("LIKE");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>NOT LIKE</em>
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator NOT_LIKE = new PredicateOperator("NOT LIKE", LIKE);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>IN</em>
   * {@link CriteriaExpression#getArg2() second argument} (list).
   */
  public static final PredicateOperator IN = new PredicateOperator("IN");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} is <em>NOT IN</em>
   * {@link CriteriaExpression#getArg2() second argument} (list).
   */
  public static final PredicateOperator NOT_IN = new PredicateOperator("NOT IN", IN);

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} (collection) <em>CONTAINS</em> the
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator CONTAINS = new PredicateOperator("CONTAINS");

  /**
   * Operator to check if {@link CriteriaExpression#getArg1() first argument} (collection) <em>NOT CONTAINS</em> the
   * {@link CriteriaExpression#getArg2() second argument}.
   */
  public static final PredicateOperator NOT_CONTAINS = new PredicateOperator("NOT CONTAINS", CONTAINS);

  /** Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>AND</em>. */
  public static final PredicateOperator AND = new PredicateOperator("AND");

  /**
   * Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>NAND</em>. Typically normalized
   * as {@code NOT(arg1 AND arg2 ...)}.
   */
  public static final PredicateOperator NAND = new PredicateOperator("NAND", AND);

  /** Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>OR</em>. */
  public static final PredicateOperator OR = new PredicateOperator("OR");

  /**
   * Operator to combine {@link CriteriaExpression#getArgs() arguments} with logical <em>NOR</em>. Typically normalized
   * as {@code NOT(arg1 OR arg2 ...)}.
   */
  public static final PredicateOperator NOR = new PredicateOperator("NOR", OR);

  /** Operator to negate a single {@link CriteriaExpression#getArgs() argument} (<em>NOT</em>). */
  public static final PredicateOperator NOT = new PredicateOperator("NOT", null, true);

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() syntax}.
   */
  protected PredicateOperator(String syntax) {

    super(syntax);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   * @param not the {@link #not() negated} form or {@code null}.
   */
  protected PredicateOperator(String syntax, Operator not) {

    super(syntax, not);
  }

  /**
   * The constructor.
   *
   * @param syntax the {@link #getSyntax() name}.
   * @param not the {@link #not() negated} form or {@code null}.
   * @param inverse the {@link #isInverse() inverse flag}.
   */
  protected PredicateOperator(String syntax, Operator not, boolean inverse) {

    super(syntax, not, inverse);
  }

  @Override
  public PredicateOperator not() {

    return (PredicateOperator) super.not();
  }

  @Override
  public boolean isInfix() {

    if (this == NOT) {
      return false;
    }
    return true;
  }

  /**
   * @return {@code true} if one of the conjunctions {@link #AND}, {@link #OR}, {@link #NAND}, or {@link #NOR} and
   *         {@code false} otherwise.
   */
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
  public CriteriaPredicate criteria(List<Supplier<?>> args) {

    if ((args == null) || args.isEmpty()) {
      throw new ObjectNotFoundException("Arguments");
    }
    if (isConjunction()) {
      CriteriaPredicate[] predicates = new CriteriaPredicate[args.size()];
      int i = 0;
      try {
        for (Supplier<?> arg : args) {
          predicates[i] = (CriteriaPredicate) arg;
          i++;
        }
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Argument at index " + i + " is not a predicate", e);
      }
      return new ConjunctionPredicate(this, predicates);
    } else {
      int argCount = args.size();
      if (argCount <= 2) {
        Supplier<?> arg1 = args.get(0);
        Supplier<?> arg2 = null;
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

    Operator op = Operator.of(syntax);
    if (op instanceof PredicateOperator) {
      return (PredicateOperator) op;
    }
    return null;
  }

}
