/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.base.exception.RuntimeIoException;
import io.github.mmm.value.PropertyPath;

/**
 * A formatter to write (pseudo) SQL to a given {@link Appendable}.
 *
 * @since 1.0.0
 */
public class CriteriaSqlFormatter implements CriteriaVisitor {

  /** {@link Appendable} where to {@link Appendable#append(CharSequence) append} the SQL. */
  protected final Appendable out;

  /**
   * The constructor.
   */
  public CriteriaSqlFormatter() {

    this(new StringBuilder(64));
  }

  /**
   * The constructor.
   *
   * @param out the {@link Appendable} to {@link #write(String) write} to.
   */
  public CriteriaSqlFormatter(Appendable out) {

    super();
    this.out = out;
  }

  /**
   * @param text the SQL to append.
   */
  protected void write(String text) {

    try {
      this.out.append(text);
    } catch (IOException e) {
      throw new RuntimeIoException(e);
    }
  }

  @Override
  public CriteriaSqlFormatter onExpression(CriteriaExpression<?> expression, CriteriaExpression<?> parent) {

    Operator op = expression.getOperator();
    boolean useBrackets = false;
    if (op.isConjunction()) {
      if (op.isInverse()) {
        useBrackets = true;
        write("NOT(");
        op = op.not();
      } else {
        useBrackets = isUseBrackets(expression, parent);
        if (useBrackets) {
          write("(");
        }
      }
    }
    int argCount = expression.getArgCount();
    if (argCount <= 2) {
      if (op.isInfix()) {
        onArg(expression, 0, expression.getFirstArg());
        write(" ");
        onOperator(op);
      } else {
        onOperator(op);
        write(" ");
        onArg(expression, 0, expression.getFirstArg());
      }
      if (argCount == 2) {
        write(" ");
        onArg(expression, 1, expression.getSecondArg());
      }
    } else {
      List<? extends Supplier<?>> args = expression.getArgs();
      assert (args.size() == argCount);
      String separator = ",";
      if (op.isInfix()) {
        separator = " " + op.toString() + " ";
      } else {
        // actually a prefix operator should always be unary and therefore we could never get here...
        assert (!op.isUnary());
        onOperator(op);
        useBrackets = true;
        write("(");
      }
      String s = "";
      for (int i = 0; i < argCount; i++) {
        write(s);
        onArg(expression, i, args.get(i));
        s = separator;
      }
    }
    if (useBrackets) {
      write(")");
    }
    return this;
  }

  /**
   * @param expression the {@link CriteriaExpression} to consider enclosing in brackets.
   * @param parent the parent {@link CriteriaExpression} using {@code expression} as {@link CriteriaExpression#getArgs()
   *        argument}.
   * @return {@code true} if {@code expression} needs to be enclosed in brackets, {@code false} otherwise.
   */
  public static boolean isUseBrackets(CriteriaExpression<?> expression, CriteriaExpression<?> parent) {

    // (a AND (b AND c)) = a AND b AND c
    // (a AND (b OR c)) = a AND (b OR c)
    // (a OR (b AND c)) = a OR b AND c
    // (a NAND (b NOR c)) = NOT(a AND NOT(b OR c))
    Operator op = expression.getOperator();
    if (parent == null) {
      return false;
    }
    if (op.isInverse()) {
      return true; // e.g. NOT(a AND b)
    }
    Operator parentOp = parent.getOperator();
    if (op == parentOp) {
      return false;
    }
    if (!parentOp.isConjunction()) {
      return false;
    }
    if (parentOp == PredicateOperator.OR) {
      return false;
    }
    return true;
  }

  @Override
  public void onOperator(Operator operator) {

    write(operator.toString());
    CriteriaVisitor.super.onOperator(operator);
  }

  @Override
  public void onPropertyPath(PropertyPath<?> property) {

    write(property.path());
    CriteriaVisitor.super.onPropertyPath(property);
  }

  @Override
  public void onLiteral(Literal<?> literal) {

    write(literal.toString());
    CriteriaVisitor.super.onLiteral(literal);
  }

  @Override
  public void onUndefinedArg(Supplier<?> arg) {

    write(arg.toString());
    CriteriaVisitor.super.onUndefinedArg(arg);
  }

  @Override
  public String toString() {

    return this.out.toString();
  }

}
