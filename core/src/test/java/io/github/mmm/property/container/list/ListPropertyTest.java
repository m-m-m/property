package io.github.mmm.property.container.list;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Iterator;
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

  @Override
  protected void verifyReadOnlyValue(ListProperty<Object> readOnly) {

    List<Object> list = readOnly.get();
    assertThat(list).isEqualTo(EXAMPLE_VALUE);
    assertThrows(UnsupportedOperationException.class, () -> list.add("bar"));
    assertThrows(UnsupportedOperationException.class, () -> list.add(0, "bar"));
    assertThrows(UnsupportedOperationException.class, () -> list.remove("foo"));
    assertThrows(UnsupportedOperationException.class, () -> {
      Iterator<Object> iterator = list.iterator();
      assertThat(iterator).hasNext();
      assertThat(iterator.next()).isEqualTo("foo");
      iterator.remove();
    });
  }

}
