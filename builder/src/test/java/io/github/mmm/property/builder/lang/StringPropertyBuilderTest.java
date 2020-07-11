/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder.lang;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.github.mmm.property.builder.PropertyBuilderTest;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.validation.ValidationResult;

/**
 * Test of {@link StringPropertyBuilder}.
 *
 * @since 1.0.0
 */
public class StringPropertyBuilderTest extends PropertyBuilderTest<String> {

  @Override
  protected StringPropertyBuilder createBuilder() {

    return new StringPropertyBuilder();
  }

  @Override
  protected String getExampleValue() {

    return "Hello World!";
  }

  /**
   * Test of {@link StringPropertyBuilder}.
   */
  @Test
  public void testBuilder() {

    // given
    StringPropertyBuilder builder = createBuilder();
    String name = "MyProperty";
    // when
    StringProperty property = builder.withValidator().mandatory().range("10", "20")
        .pattern(Pattern.compile("[a-zA-Z0-9]*")).and().build(name);
    property.set("HelloWorld");
    ValidationResult result = property.validate();
    // then
    assertThat(result.isValid()).as(result.toString()).isTrue();
    // but when
    property.set("Hi World");
    result = property.validate();
    // then
    assertThat(result.isValid()).isFalse();
    assertThat(result.getMessage()).isEqualTo(
        "The length needs to be in the range from 10 to 20.\nThe value has to match the format '[a-zA-Z0-9]*'.");
    assertThat(result.getSource()).isEqualTo(name);
    // or when
    property.set(null);
    result = property.validate();
    // then
    assertThat(result.isValid()).isFalse();
    assertThat(result.getMessage()).isEqualTo(ERR_MANDATORY);
  }

}
