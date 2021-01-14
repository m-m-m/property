/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link PredicateOperator}.
 */
public class PredicateOperatorTest extends Assertions {

  /** Tests all "enum" constants of {@link PredicateOperator}. */
  @Test
  public void testAll() {

    check(PredicateOperator.EQ, "=", PredicateOperator.NEQ, "<>");
    check(PredicateOperator.IS_NULL, "IS NULL", PredicateOperator.IS_NOT_NULL, "IS NOT NULL");
    check(PredicateOperator.LIKE, "LIKE", PredicateOperator.NOT_LIKE, "NOT LIKE");
    check(PredicateOperator.AND, "AND", PredicateOperator.NAND, "NAND");
    check(PredicateOperator.OR, "OR", PredicateOperator.NOR, "NOR");
    check(PredicateOperator.IN, "IN", PredicateOperator.NOT_IN, "NOT IN");
    check(PredicateOperator.CONTAINS, "CONTAINS", PredicateOperator.NOT_CONTAINS, "NOT CONTAINS");
    check(PredicateOperator.GT, ">", PredicateOperator.LE, "<=", false);
    check(PredicateOperator.GE, ">=", PredicateOperator.LT, "<", false);
    check(PredicateOperator.NOT, "NOT", true);
    assertThat(PredicateOperator.NOT.not()).isNull();
  }

  private void check(PredicateOperator normal, String syntax, PredicateOperator negated, String notSyntax) {

    check(normal, syntax, negated, notSyntax, true);
  }

  private void check(PredicateOperator normal, String syntax, PredicateOperator negated, String notSyntax,
      boolean inverse) {

    check(normal, syntax, false);
    check(negated, notSyntax, inverse);
    assertThat(normal.not()).isSameAs(negated);
    assertThat(negated.not()).isSameAs(normal);
  }

  private void check(PredicateOperator op, String syntax, boolean inverse) {

    assertThat(op).isNotNull();
    assertThat(op.getSyntax()).isSameAs(op.toString()).isEqualTo(syntax);
    assertThat(op.isInverse()).isEqualTo(inverse);
    assertThat(PredicateOperator.of(syntax)).isSameAs(op);
  }

  /**
   *
   */
  @Test
  public void testCustomEnums() {

    // ATTENTION: will fail before class AdvancedPredicateOperator has been loaded and initialized by classloader
    // assertThat(PredicateOperator.of("IS COOL")).isSameAs(AdvancedPredicateOperator.IS_COOL);
    assertThat(AdvancedPredicateOperator.of("IS COOL")).isSameAs(AdvancedPredicateOperator.IS_COOL);
    check(AdvancedPredicateOperator.IS_COOL, "IS COOL", AdvancedPredicateOperator.IS_NOT_COOL, "IS NOT COOL");
  }

  static class AdvancedPredicateOperator extends PredicateOperator {

    static final AdvancedPredicateOperator IS_COOL = new AdvancedPredicateOperator("IS COOL");

    static final AdvancedPredicateOperator IS_NOT_COOL = new AdvancedPredicateOperator("IS NOT COOL", IS_COOL);

    AdvancedPredicateOperator(String syntax, Operator not, boolean inverse) {

      super(syntax, not, inverse);
    }

    AdvancedPredicateOperator(String syntax, Operator not) {

      super(syntax, not);
    }

    AdvancedPredicateOperator(String syntax) {

      super(syntax);
    }

    /**
     * @param syntax the {@link #getSyntax() syntax} of the requested {@link AdvancedPredicateOperator}.
     * @return the predefined {@link AdvancedPredicateOperator} or {@code null} if no such operator exists.
     */
    public static AdvancedPredicateOperator of(String syntax) {

      Operator op = Operator.of(syntax);
      if (op instanceof AdvancedPredicateOperator) {
        return (AdvancedPredicateOperator) op;
      }
      return null;
    }
  }

}
