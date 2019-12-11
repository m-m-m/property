/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import org.junit.jupiter.api.Test;

import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.builder.PropertyBuilderTest;
import io.github.mmm.validation.ValidationResult;

/**
 * Test of {@link BooleanPropertyBuilder}.
 */
public class BooleanPropertyBuilderTest extends PropertyBuilderTest<Boolean> {

  @Override
  protected BooleanPropertyBuilder createBuilder() {

    return new BooleanPropertyBuilder();
  }

  @Override
  protected Boolean getExampleValue() {

    return Boolean.TRUE;
  }

  /**
   * Test of {@link BooleanPropertyBuilder}.
   */
  @Test
  public void testBuilder() {

    // given
    BooleanPropertyBuilder builder = createBuilder();
    String name = "MyProperty";
    // when
    BooleanProperty property = builder.withValidator().mandatory().and().build(name);
    ValidationResult result = property.validate();
    // then
    assertThat(result.isValid()).as(result.toString()).isFalse();
    assertThat(result.getMessage()).isEqualTo("The value has to be filled.");
    // but when
    property.set(Boolean.TRUE);
    result = property.validate();
    // then
    assertThat(result.isValid()).isTrue();
  }

}
