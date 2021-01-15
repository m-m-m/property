/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.io.IOException;
import java.util.Collection;
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
  public void onExpression(CriteriaExpression<?> expression) {

    Operator op = expression.getOperator();
    int argCount = expression.getArgCount();
    if (argCount <= 2) {
      if (op.isInfix()) {
        onArg(expression.getArg1());
        write(" ");
        onOperator(op);
      } else {
        onOperator(op);
        write(" ");
        onArg(expression.getArg1());
      }
      if (argCount == 2) {
        write(" ");
        onArg(expression.getArg2());
      }
    } else {
      Collection<? extends Supplier<?>> args = expression.getArgs();
      String separator = ",";
      if (op.isInfix()) {
        separator = " " + op.toString() + " ";
      } else {
        onOperator(op);
        write("(");
      }
      String s = "";
      for (Supplier<?> arg : args) {
        write(s);
        onArg(arg);
        s = separator;
      }
      if (!op.isInfix()) {
        write(")");
      }
    }
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

    Object value = literal.get();
    if (value instanceof String) {
      write("'");
      write(((String) value).replace("'", "\\'"));
      write("'");
    } else {
      write(value.toString());
    }
    CriteriaVisitor.super.onLiteral(literal);
  }

  @Override
  public void onUndefinedArg(Supplier<?> arg) {

    write(arg.toString());
    CriteriaVisitor.super.onUndefinedArg(arg);
  }

}
