package io.github.mmm.property.number.biginteger;

import java.math.BigInteger;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link BigIntegerProperty}.
 */
public class BigIntegerPropertyTest extends PropertyTest<BigInteger, BigIntegerProperty> {

  BigIntegerPropertyTest() {

    super(BigInteger.ONE, BigIntegerProperty.class);
  }

}
