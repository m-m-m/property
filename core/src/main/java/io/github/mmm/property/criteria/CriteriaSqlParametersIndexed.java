/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.base.io.AppendableWriter;

/**
 * {@link CriteriaSqlParameters} using indexed parameters. It puts "{@code ?}" into the SQL and collects the parameters
 * in a {@link List} that can get accessed via {@link #getParameters()} after the SQL {@link String} has been created
 * and can be bound as parameters to the statement.
 *
 * @since 1.0.0
 */
public class CriteriaSqlParametersIndexed implements CriteriaSqlParameters {

  private final List<Object> parameters;

  /**
   * The constructor.
   */
  public CriteriaSqlParametersIndexed() {

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
   * @return the {@link List} of collected indexed parameter values to bind to the SQL statement.
   */
  public List<Object> getParameters() {

    return this.parameters;
  }

}
