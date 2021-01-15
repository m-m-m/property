/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.List;

/**
 * {@link CriteriaSqlFormatter} using indexed parameters.
 *
 * @since 1.0.0
 */
public class CriteriaSqlFormatterIndexedParameters extends CriteriaSqlFormatter {

  private List<Object> parameters;

  /**
   * The constructor.
   *
   * @param out the {@link Appendable} to {@link #write(String) write} to.
   */
  public CriteriaSqlFormatterIndexedParameters(Appendable out) {

    super(out);
  }

  @Override
  public void onLiteral(Literal<?> literal) {

    this.parameters.add(literal.get());
    write("?");
  }

  /**
   * @return the {@link List} of parameter values to bind to the query.
   */
  public List<Object> getParameters() {

    return this.parameters;
  }

}
