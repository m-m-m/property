/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.base.io.AppendableWriter;

/**
 * {@link CriteriaParameters} using indexed parameters. It puts "{@code ?}" for each {@link Literal} into the output and
 * collects the parameters in a {@link List} that can get accessed via {@link #getParameters()} after the database
 * statement has been formatted and can be bound as parameters to the statement.
 *
 * @since 1.0.0
 */
public class CriteriaParametersIndexed implements CriteriaParameters {

  private final List<Object> parameters;

  /**
   * The constructor.
   */
  public CriteriaParametersIndexed() {

    super();
    this.parameters = new ArrayList<>();
  }

  @Override
  public void onLiteral(Literal<?> literal, AppendableWriter out, CriteriaExpression<?> parent) {

    if (literal != null) {
      Object value = literal.get();
      if (value != null) {
        this.parameters.add(value);
        out.append('?');
        return;
      }
    }
    out.append("null");
  }

  /**
   * @return the {@link List} of collected indexed parameter values to bind to the database statement.
   */
  public List<Object> getParameters() {

    return this.parameters;
  }

}
