/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.github.mmm.base.io.AppendableWriter;
import io.github.mmm.value.CriteriaSelection;
import io.github.mmm.value.PropertyPath;

/**
 * {@link CriteriaParameters} using named parameters. It puts "{@code :«name»}" into the output and collects the
 * parameters in a {@link Map} that can get accessed via {@link #getParameters()} after the database statement has been
 * formatted and can be bound as parameters to the statement.
 *
 * @since 1.0.0
 */
public class CriteriaParametersNamed implements CriteriaParameters {

  private final Map<String, Object> parameters;

  private final boolean merge;

  /**
   * The constructor.
   *
   * @param merge the {@link #isMerge() merge flag}.
   */
  public CriteriaParametersNamed(boolean merge) {

    super();
    this.parameters = new HashMap<>();
    this.merge = merge;
  }

  /**
   * @return {@code true} if two {@link Literal}s with same parameter name should be joined if their
   *         {@link Literal#get() value}s are {@link Object#equals(Object) equal} to each other, {@code false}
   *         otherwise. So if your SQL has {@code p.Property() = 42 AND q.Property() = 42} a merge would create just one
   *         parameter ":property" with the value "42" and without merge it would add two parameters ":property" and
   *         ":property2" both with the value "42".
   */
  public boolean isMerge() {

    return this.merge;
  }

  @Override
  public void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent) {

    if (literal != null) {
      Object value = literal.get();
      if (value != null) {
        String name = findName(parent);
        if (name == null) {
          name = "arg";
        }
        name = setParameter(name, value);
        out.append(':');
        out.append(name);
        return;
      }
    }
    out.append("null");
  }

  private String setParameter(String name, Object value) {

    String finalName = name;
    int i = 2;
    if (this.parameters.containsKey(finalName)) {
      if ((i == 2) && this.merge) {
        Object old = this.parameters.get(finalName);
        if (Objects.equals(old, value)) {
          return name;
        }
      }
      finalName = name + i++;
    }
    Object old = this.parameters.put(finalName, value);
    assert (old == null);
    return finalName;
  }

  private String findName(CriteriaExpression<?> expression) {

    if (expression == null) {
      return null;
    }
    String name = findName(expression.getFirstArg());
    if (name == null) {
      name = findName(expression.getSecondArg());
      if (name == null) {
        int argCount = expression.getArgCount();
        if (argCount > 2) {
          List<? extends CriteriaSelection<?>> args = expression.getArgs();
          int i = 2;
          while ((name == null) && (i < argCount)) {
            name = findName(args.get(i++));
          }
        }
      }
    }
    return name;
  }

  private String findName(CriteriaSelection<?> arg) {

    if (arg == null) {
      return null;
    } else if (arg instanceof PropertyPath) {
      return ((PropertyPath<?>) arg).getName();
    } else if (arg instanceof CriteriaExpression) {
      return findName((CriteriaExpression<?>) arg);
    }
    return null;
  }

  /**
   * @return the {@link Map} of collected named parameter values to bind to the database statement.
   */
  public Map<String, Object> getParameters() {

    return this.parameters;
  }

}
