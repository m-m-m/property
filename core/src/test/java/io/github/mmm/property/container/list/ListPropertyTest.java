package io.github.mmm.property.container.list;

import java.util.ArrayList;
import java.util.List;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link ListProperty}.
 */
class ListPropertyTest extends PropertyTest<List<Object>, ListProperty<Object>> {

  private static final List<Object> EXAMPLE_VALUE = new ArrayList<>();

  static {

    EXAMPLE_VALUE.add("foo");
    EXAMPLE_VALUE.add(4711);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  ListPropertyTest() {

    super(EXAMPLE_VALUE, (Class) ListProperty.class);
  }

}
