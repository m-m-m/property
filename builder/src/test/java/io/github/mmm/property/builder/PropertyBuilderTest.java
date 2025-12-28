/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.property.Property;
import io.github.mmm.property.container.list.ListProperty;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.main.ValidatorMandatory;

/**
 * Abstract base test of {@link PropertyBuilder}.
 *
 * @param <V> type of {@link Property#get() property value}.
 */
public abstract class PropertyBuilderTest<V> extends Assertions {

  /** Untranslated error message of mandatory validator. */
  protected static final String ERR_MANDATORY = "The value has to be filled.";

  /**
   * @return a new instance of the {@link PropertyBuilder} to test.
   */
  protected abstract PropertyBuilder<V, ?, ?, ?> createBuilder();

  /**
   * @return a valid example value.
   */
  protected abstract V getExampleValue();

  /**
   * Test basic features of {@link PropertyBuilder}.
   */
  @Test
  public void testBasics() {

    // arrange
    PropertyBuilder<V, ?, ?, ?> builder = createBuilder();
    String name = "MyProperty";
    // act
    Property<V> property = builder.build(name);
    // assert
    assertThat(property).isNotNull();
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.get()).isNull();
    // class is not hidden outside module
    assertThat(property.getMetadata().getClass().getSimpleName()).isEqualTo("PropertyMetadataNone");
    assertThat(property.validate().isValid()).isTrue();

    // act again
    builder.withValidator().mandatory().and();
    property = builder.build(name);
    assertThat(property).isNotNull();
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.get()).isNull();
    assertThat(property.getMetadata().getValidator()).isSameAs(ValidatorMandatory.get());
    ValidationResult result = property.validate();
    assertThat(result.isValid()).isFalse();
    assertThat(result.getMessage()).isEqualTo(ERR_MANDATORY);
    property.set(getExampleValue());
    assertThat(property.validate().isValid()).isTrue();

    // act again
    property = builder.metaInfo("key", "value").build(name);
    assertThat(property).isNotNull();
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.get()).isNull();
    assertThat(property.getMetadata().getValidator()).isSameAs(ValidatorMandatory.get());
    assertThat(property.getMetadata().getMetaInfo().get("key")).isEqualTo("value");
  }

  /**
   * Test of {@link PropertyBuilder#asList()}.
   */
  @Test
  public void testAsList() {

    // arrange
    PropertyBuilder<V, ?, ?, ?> builder = createBuilder();
    String name = "MyProperty";
    // act
    builder.withValidator().mandatory().and();
    ListProperty<V> property = builder.asList().withValidator().size(1, 2).and().build(name);
    // assert
    assertThat(property).isNotNull();
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.get()).isNull();
    List<V> value = property.getOrCreate();
    assertThat(property.get()).isNotNull().isSameAs(value);
    ValidationResult result = property.validate();
    assertThat(result.isValid()).isFalse();
    assertThat(result.getMessage()).isEqualTo("The length needs to be in the range from 1 to 2.");
    value.add(getExampleValue());
    result = property.validate();
    assertThat(result.isValid()).as(result.toString()).isTrue();
    value.add(null);
    result = property.validate();
    assertThat(result.isValid()).as(result.toString()).isFalse();
    assertThat(result.getMessage()).isEqualTo(ERR_MANDATORY);
  }

}
