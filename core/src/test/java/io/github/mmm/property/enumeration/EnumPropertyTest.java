package io.github.mmm.property.enumeration;

import org.junit.jupiter.api.Test;

import io.github.mmm.marshall.StandardFormat;
import io.github.mmm.marshall.StructuredTextFormat;
import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link EnumProperty}.
 */
public class EnumPropertyTest extends PropertyTest<TestEnum, EnumProperty<TestEnum>> {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  EnumPropertyTest() {

    super(TestEnum.BAR, (Class) EnumProperty.class);
  }

  /** Test that {@link EnumProperty#getSafe()} uses first enum constant as fallback. */
  @Test
  public void testSafe() {

    // arrange
    EnumProperty<TestEnum> enumProperty = new EnumProperty<>("enum", null, TestEnum.class);
    // act
    TestEnum safeValue = enumProperty.getSafe();
    // assert
    assertThat(safeValue).isSameAs(TestEnum.FOO).isSameAs(enumProperty.getFallbackSafeValue());
  }

  /** Test that {@link EnumProperty} can be read from its {@link Enum#toString()} value as JSON. */
  @Test
  public void testReadJson() {

    // arrange
    EnumProperty<TestEnum> enumProperty = new EnumProperty<>("enum", null, TestEnum.class);
    String json = "\"bar2\""; // "bar2" = TestEnum.BAR.toString()
    StructuredTextFormat jsonFormat = StandardFormat.json();
    // act
    jsonFormat.read(json, enumProperty);
    // assert
    assertThat(enumProperty.get()).isSameAs(TestEnum.BAR);
  }

  /** Test that {@link EnumProperty} writes its {@link Enum#toString()} value as JSON. */
  @Test
  public void testWriteJson() {

    // arrange
    EnumProperty<TestEnum> enumProperty = new EnumProperty<>("enum", null, TestEnum.class);
    enumProperty.set(TestEnum.BAR);
    StructuredTextFormat jsonFormat = StandardFormat.json();
    // act
    String json = jsonFormat.write(enumProperty);
    // assert
    assertThat(json).isEqualTo("\"bar2\""); // "bar2" = TestEnum.BAR.toString()
  }

}
