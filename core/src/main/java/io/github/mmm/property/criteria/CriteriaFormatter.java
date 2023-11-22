/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;

import io.github.mmm.base.io.AppendableWriter;
import io.github.mmm.property.criteria.impl.CriteriaParametersInline;
import io.github.mmm.value.CriteriaObject;
import io.github.mmm.value.PropertyPath;

/**
 * A formatter to format {@link CriteriaExpression}s to database specific notation (e.g. SQL) {@link #toString() as
 * string} to a given {@link Appendable} (see {@link #out()}). See {@code of*} methods to create instances. For specific
 * database dialects simply create a subclass of this {@link CriteriaFormatter}.
 *
 * @since 1.0.0
 */
public class CriteriaFormatter implements CriteriaVisitor {

  /** {@link Appendable} where to {@link Appendable#append(CharSequence) append} the SQL. */
  protected final AppendableWriter out;

  private final CriteriaParameters<?> parameters;

  private LikePatternSyntax likeSyntaxSource;

  private LikePatternSyntax likeSyntaxTarget;

  /**
   * The constructor using inline {@link CriteriaParameters}. <br>
   * <b>ATTENTION:</b> Only use this for testing or debugging (e.g. in {@link #toString()}) to avoid SQL-injection
   * security vulnerabilities.
   */
  public CriteriaFormatter() {

    this(null, new AppendableWriter(new StringBuilder()));
  }

  /**
   * The constructor.
   *
   * @param parameters the {@link CriteriaParameters} or {@code null} for default (inline). <b>ATTENTION:</b> Use
   *        {@code null} only for testing or debugging (e.g. in {@link #toString()}) to avoid SQL-injection security
   *        vulnerabilities.
   * @param out the {@link Appendable} to {@link #write(String) write} to.
   */
  protected CriteriaFormatter(CriteriaParameters<?> parameters, AppendableWriter out) {

    super();
    if (parameters == null) {
      this.parameters = CriteriaParametersInline.get();
    } else {
      this.parameters = parameters;
    }
    this.out = out;
    this.likeSyntaxTarget = LikePatternSyntax.SQL;
  }

  /**
   * @return likeSyntaxSource
   */
  public LikePatternSyntax getLikeSyntaxSource() {

    return this.likeSyntaxSource;
  }

  /**
   * @param likeSyntaxSource new value of {@link #getLikeSyntaxSource()}.
   */
  public void setLikeSyntaxSource(LikePatternSyntax likeSyntaxSource) {

    this.likeSyntaxSource = likeSyntaxSource;
  }

  /**
   * @return likeSyntaxTarget
   */
  public LikePatternSyntax getLikeSyntaxTarget() {

    return this.likeSyntaxTarget;
  }

  /**
   * @param likeSyntaxTarget new value of {@link #getLikeSyntaxTarget()}.
   */
  public void setLikeSyntaxTarget(LikePatternSyntax likeSyntaxTarget) {

    this.likeSyntaxTarget = likeSyntaxTarget;
  }

  /**
   * @param text the database syntax to append.
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
   * @return the {@link CriteriaParameters}.
   */
  public CriteriaParameters<?> getParameters() {

    return this.parameters;
  }

  @Override
  public CriteriaFormatter onExpression(CriteriaExpression<?> expression, CriteriaExpression<?> parent) {

    CriteriaOperator op = expression.getOperator();
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
      if (argCount == 0) {
        onOperator(op);
      } else if (op.isInfix()) {
        onArg(expression.getFirstArg(), 0, expression);
        write(" ");
        onOperator(op);
      } else {
        onOperator(op);
        write("(");
        useBrackets = true;
        onArg(expression.getFirstArg(), 0, expression);
      }
      if (argCount == 2) {
        write(" ");
        onArg(expression.getSecondArg(), 1, expression);
      }
    } else {
      List<? extends CriteriaObject<?>> args = expression.getArgs();
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
    CriteriaOperator op = expression.getOperator();
    if (parent == null) {
      return false;
    }
    if (op.isInverse()) {
      return true; // e.g. NOT(a AND b)
    }
    CriteriaOperator parentOp = parent.getOperator();
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
  public void onOperator(CriteriaOperator operator) {

    write(operator.toString());
  }

  @Override
  public void onPropertyPath(PropertyPath<?> property, int i, CriteriaExpression<?> parent) {

    write(property.path());
  }

  @Override
  public void onProjectionProperty(ProjectionProperty<?> arg, int i, CriteriaExpression<?> parent) {

    CriteriaObject<?> selection = arg.getSelection();
    onArg(selection, i, parent);
    PropertyPath<?> property = arg.getProperty();
    boolean writeAlias = true;
    if (selection instanceof PropertyPath) {
      writeAlias = !PropertyPathHelper.isEqualPath((PropertyPath<?>) selection, property, true);
    }
    if (writeAlias) {
      write(" ");
      onAlias(property.path());
    }
  }

  /**
   * Writes the given {@code alias}. Override to {@link #write(String) write} "AS " as prefix if required or desired.
   *
   * @param alias the alias to {@link #write(String) write}.
   */
  public void onAlias(String alias) {

    write(alias);
  }

  @Override
  public void onLiteral(Literal<?> literal, int i, CriteriaExpression<?> parent) {

    if ((this.likeSyntaxSource != this.likeSyntaxTarget) && (parent != null)) {
      CriteriaOperator op = parent.getOperator();
      if (PredicateOperator.isLikeBased(op)) {
        Object value = literal.get();
        if (value instanceof String) {
          String pattern = (String) value;
          literal = Literal.of(this.likeSyntaxTarget.convert(pattern, this.likeSyntaxSource));
        }
      }
    }
    this.parameters.onLiteral(literal, this.out, parent);
    CriteriaVisitor.super.onLiteral(literal, i, parent);
  }

  /**
   * @param glob the literal value for a LIKE expression assumed in glob-syntax.
   * @return the given {@code glob} {@link String} converted to LIKE pattern.
   */
  protected String convertLikePattern(String glob) {

    int length = glob.length();
    StringBuilder sb = new StringBuilder(length + 2);
    int i = 0;
    while (i < length) {

    }
    return sb.toString();
  }

  @Override
  public void onUndefinedArg(CriteriaObject<?> arg, int i, CriteriaExpression<?> parent) {

    write(arg.toString());
    CriteriaVisitor.super.onUndefinedArg(arg, i, parent);
  }

  /**
   * @param ordering the {@link CriteriaOrdering} to format to append.
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
   * @param parameters the {@link CriteriaParameters}.
   * @return the new {@link CriteriaFormatter}.
   */
  public static CriteriaFormatter of(CriteriaParameters<?> parameters) {

    return of(parameters, new StringBuilder());
  }

  /**
   * @param parameters the {@link CriteriaParameters}.
   * @param appendable the {@link Appendable} where to write the database syntax to.
   * @return the new {@link CriteriaFormatter}.
   */
  public static CriteriaFormatter of(CriteriaParameters<?> parameters, Appendable appendable) {

    return of(parameters, new AppendableWriter(appendable));
  }

  /**
   * @param parameters the {@link CriteriaParameters}.
   * @param appendable the {@link AppendableWriter} where to write the database syntax to.
   * @return the new {@link CriteriaFormatter}.
   */
  public static CriteriaFormatter of(CriteriaParameters<?> parameters, AppendableWriter appendable) {

    return new CriteriaFormatter(parameters, appendable);
  }

  // /**
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersIndexed indexed parameters}.
  // */
  // public static CriteriaFormatter ofIndexedParameters() {
  //
  // return ofIndexedParameters(new AppendableWriter(new StringWriter()));
  // }
  //
  // /**
  // * @param appendable the {@link Appendable} where to write the database syntax to.
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersIndexed indexed parameters}.
  // */
  // public static CriteriaFormatter ofIndexedParameters(Appendable appendable) {
  //
  // return ofIndexedParameters(new AppendableWriter(appendable));
  // }
  //
  // /**
  // * @param appendable the {@link AppendableWriter} where to write the database syntax to.
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersIndexed indexed parameters}.
  // */
  // public static CriteriaFormatter ofIndexedParameters(AppendableWriter appendable) {
  //
  // CriteriaParametersIndexed parameters = new CriteriaParametersIndexed();
  // return new CriteriaFormatter(parameters, appendable);
  // }
  //
  // /**
  // * @param appendable the {@link Appendable} where to write the database syntax to.
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersNamed named parameters}.
  // */
  // public static CriteriaFormatter ofNamedParameters(Appendable appendable) {
  //
  // return ofNamedParameters(appendable, false);
  // }
  //
  // /**
  // * @param appendable the {@link Appendable} where to write the database syntax to.
  // * @param merge the {@link CriteriaParametersNamed#isMerge() merge flag}.
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersNamed named parameters}.
  // */
  // public static CriteriaFormatter ofNamedParameters(Appendable appendable, boolean merge) {
  //
  // return ofNamedParameters(new AppendableWriter(appendable), merge);
  // }
  //
  // /**
  // * @param appendable the {@link AppendableWriter} where to write the database syntax to.
  // * @param merge the {@link CriteriaParametersNamed#isMerge() merge flag}.
  // * @return the new {@link CriteriaFormatter} using {@link CriteriaParametersNamed named parameters}.
  // */
  // public static CriteriaFormatter ofNamedParameters(AppendableWriter appendable, boolean merge) {
  //
  // CriteriaParametersNamed parameters = new CriteriaParametersNamed(merge);
  // return new CriteriaFormatter(parameters, appendable);
  // }

}
