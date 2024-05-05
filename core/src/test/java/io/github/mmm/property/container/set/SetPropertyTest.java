package io.github.mmm.property.container.set;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link SetProperty}.
 */
class SetPropertyTest extends PropertyTest<Set<Object>, SetProperty<Object>> {

  private static final Set<Object> EXAMPLE_VALUE = new HashSet<>();

  static {

    EXAMPLE_VALUE.add("foo");
    EXAMPLE_VALUE.add(4711);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  SetPropertyTest() {

    super(EXAMPLE_VALUE, (Class) SetProperty.class);
  }

  @Override
  protected void verifyReadOnlyValue(SetProperty<Object> readOnly) {

    Set<Object> set = readOnly.get();
    assertThat(set).isEqualTo(EXAMPLE_VALUE);
    assertThrows(UnsupportedOperationException.class, () -> set.add("bar"));
    assertThrows(UnsupportedOperationException.class, () -> set.remove("foo"));
    assertThrows(UnsupportedOperationException.class, () -> {
      Iterator<Object> iterator = set.iterator();
      assertThat(iterator).hasNext();
      assertThat(iterator.next()).isEqualTo("foo");
      iterator.remove();
    });
  }

}
