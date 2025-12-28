package io.github.mmm.property.number.bytes;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link ByteProperty}.
 */
class BytePropertyTest extends PropertyTest<Byte, ByteProperty> {

  BytePropertyTest() {

    super(Byte.valueOf((byte) 1), ByteProperty.class);
  }

}
