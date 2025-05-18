package io.github.mmm.property.number.bigdecimal;

import java.math.BigDecimal;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link BigDecimalProperty}.
 */
public class BigDecimalPropertyTest extends PropertyTest<BigDecimal, BigDecimalProperty> {

  BigDecimalPropertyTest() {

    super(BigDecimal.ONE, BigDecimalProperty.class);
  }

}
