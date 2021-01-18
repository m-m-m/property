/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.base.io.AppendableWriter;
import io.github.mmm.value.PropertyPath;

/**
 * A formatter to write (pseudo) SQL to a given {@link Appendable}. See {@code of*} methods to create instances. For
 * specific SQL dialects simply create a subclass of this {@link CriteriaSqlFormatter}.
 *
 * @since 1.0.0
 */
public class CriteriaSqlFormatter implements CriteriaVisitor {

  /** {@link Appendable} where to {@link Appendable#append(CharSequence) append} the SQL. */
  protected final AppendableWriter out;

  private final CriteriaSqlParameters parameters;

  /**
   * The constructor using inline {@link CriteriaSqlParameters}. <br>
   * <b>ATTENTION:</b> Only use this for testing or debugging (e.g. in {@link #toString()}) to avoid SQL-injection
   * security vulnerabilities.
   */
  protected CriteriaSqlFormatter() {

    this(null, new AppendableWriter(new StringBuilder()));
  }

  /**
   * The constructor.
   *
   * @param parameters the {@link CriteriaSqlParameters} or {@code null} for default (inline). <b>ATTENTION:</b> Use
   *        {@code null} only for testing or debugging (e.g. in {@link #toString()}) to avoid SQL-injection security
   *        vulnerabilities.
   * @param out the {@link Appendable} to {@link #write(String) write} to.
   */
  protected CriteriaSqlFormatter(CriteriaSqlParameters parameters, AppendableWriter out) {

    super();
    if (parameters == null) {
      this.parameters = CriteriaSqlParametersInline.get();
    } else {
      this.parameters = parameters;
    }
    this.out = out;
  }

  /**
   * @param text the SQL to append.
   */
  protected void write(String text) {

    this.out.append(text);
  }

  /**
   * @return the {@link AppendableWriter} wrapping the {@link Appendable} to write to.
   */
  public AppendableWriter out() {

    return this.out;
  }

  /**
   * @return the {@link CriteriaSqlParameters}.
   */
  public CriteriaSqlParameters getParameters() {

    return this.parameters;
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
        onArg(expression.getFirstArg(), 0, expression);
        write(" ");
        onOperator(op);
      } else {
        onOperator(op);
        write(" ");
        onArg(expression.getFirstArg(), 0, expression);
      }
      if (argCount == 2) {
        write(" ");
        onArg(expression.getSecondArg(), 1, expression);
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
        onArg(args.get(i), i, expression);
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
  public void onPropertyPath(PropertyPath<?> property, int i, CriteriaExpression<?> parent) {

    write(property.path());
    CriteriaVisitor.super.onPropertyPath(property, i, parent);
  }

  @Override
  public void onLiteral(Literal<?> literal, int i, CriteriaExpression<?> parent) {

    this.parameters.onLiteral(literal, this.out, parent);
    CriteriaVisitor.super.onLiteral(literal, i, parent);
  }

  @Override
  public void onUndefinedArg(Supplier<?> arg, int i, CriteriaExpression<?> parent) {

    write(arg.toString());
    CriteriaVisitor.super.onUndefinedArg(arg, i, parent);
  }

  /**
   * @param ordering the {@link CriteriaOrdering} to format to SQL.
   */
  public void onOrdering(CriteriaOrdering ordering) {

    onPropertyPath(ordering.getProperty(), 0, null);
    write(" ");
    write(ordering.getOrder().name());
  }

  @Override
  public String toString() {

    return this.out.toString();
  }

  /**
   * @param parameters the {@link CriteriaSqlParameters}.
   * @param appendable the {@link Appendable} where to write the SQL to.
   * @return the new {@link CriteriaSqlFormatter}.
   */
  public static CriteriaSqlFormatter of(CriteriaSqlParameters parameters, Appendable appendable) {

    return of(parameters, new AppendableWriter(appendable));
  }

  /**
   * @param parameters the {@link CriteriaSqlParameters}.
   * @param appendable the {@link AppendableWriter} where to write the SQL to.
   * @return the new {@link CriteriaSqlFormatter}.
   */
  public static CriteriaSqlFormatter of(CriteriaSqlParameters parameters, AppendableWriter appendable) {

    return new CriteriaSqlFormatter(parameters, appendable);
  }

  /**
   * @param appendable the {@link Appendable} where to write the SQL to.
   * @return the new {@link CriteriaSqlFormatter} using {@link CriteriaSqlParametersIndexed indexed parameters}.
   */
  public static CriteriaSqlFormatter ofIndexedParameters(Appendable appendable) {

    return ofIndexedParameters(new AppendableWriter(appendable));
  }

  /**
   * @param appendable the {@link AppendableWriter} where to write the SQL to.
   * @return the new {@link CriteriaSqlFormatter} using {@link CriteriaSqlParametersIndexed indexed parameters}.
   */
  public static CriteriaSqlFormatter ofIndexedParameters(AppendableWriter appendable) {

    CriteriaSqlParametersIndexed parameters = new CriteriaSqlParametersIndexed();
    return new CriteriaSqlFormatter(parameters, appendable);
  }

  /**
   * @param appendable the {@link Appendable} where to write the SQL to.
   * @return the new {@link CriteriaSqlFormatter} using {@link CriteriaSqlParametersNamed named parameters}.
   */
  public static CriteriaSqlFormatter ofNamedParameters(Appendable appendable) {

    return ofNamedParameters(appendable, false);
  }

  /**
   * @param appendable the {@link Appendable} where to write the SQL to.
   * @param merge the {@link CriteriaSqlParametersNamed#isMerge() merge flag}.
   * @return the new {@link CriteriaSqlFormatter} using {@link CriteriaSqlParametersNamed named parameters}.
   */
  public static CriteriaSqlFormatter ofNamedParameters(Appendable appendable, boolean merge) {

    return ofNamedParameters(new AppendableWriter(appendable), merge);
  }

  /**
   * @param appendable the {@link AppendableWriter} where to write the SQL to.
   * @param merge the {@link CriteriaSqlParametersNamed#isMerge() merge flag}.
   * @return the new {@link CriteriaSqlFormatter} using {@link CriteriaSqlParametersNamed named parameters}.
   */
  public static CriteriaSqlFormatter ofNamedParameters(AppendableWriter appendable, boolean merge) {

    CriteriaSqlParametersNamed parameters = new CriteriaSqlParametersNamed(merge);
    return new CriteriaSqlFormatter(parameters, appendable);
  }

}
