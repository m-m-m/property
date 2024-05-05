package io.github.mmm.property.container.map;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.assertj.core.data.MapEntry;

import io.github.mmm.property.PropertyTest;

/**
 * Test of {@link MapProperty}.
 */
class MapPropertyTest extends PropertyTest<Map<String, Object>, MapProperty<String, Object>> {

  private static final Map<String, Object> EXAMPLE_VALUE = new HashMap<>();

  static {

    EXAMPLE_VALUE.put("foo", "bar");
    EXAMPLE_VALUE.put("number", 4711);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  MapPropertyTest() {

    super(EXAMPLE_VALUE, (Class) MapProperty.class);
  }

  @Override
  protected void verifyReadOnlyValue(MapProperty<String, Object> readOnly) {

    Map<String, Object> map = readOnly.get();
    assertThat(map).isEqualTo(EXAMPLE_VALUE);
    assertThrows(UnsupportedOperationException.class, () -> map.put("bar", "foo"));
    assertThrows(UnsupportedOperationException.class, () -> map.remove("foo"));
    assertThrows(UnsupportedOperationException.class, () -> map.keySet().add("foo"));
    assertThrows(UnsupportedOperationException.class, () -> map.keySet().remove("foo"));
    assertThrows(UnsupportedOperationException.class, () -> {
      Iterator<String> iterator = map.keySet().iterator();
      if (iterator.hasNext()) {
        iterator.next();
        iterator.remove();
      } else {
        throw new UnsupportedOperationException();
      }
    });
    assertThrows(UnsupportedOperationException.class, () -> map.values().add("foo"));
    // assertThrows(UnsupportedOperationException.class, () -> map.values().remove("foo"));
    assertThrows(UnsupportedOperationException.class, () -> {
      Iterator<Object> iterator = map.values().iterator();
      if (iterator.hasNext()) {
        iterator.next();
        iterator.remove();
      } else {
        throw new UnsupportedOperationException();
      }
    });
    MapEntry<String, Object> entry = MapEntry.entry("foo", "bar");
    assertThrows(UnsupportedOperationException.class, () -> map.entrySet().add(entry));
    assertThrows(UnsupportedOperationException.class, () -> map.entrySet().remove(entry));
    assertThrows(UnsupportedOperationException.class, () -> {
      Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
      if (iterator.hasNext()) {
        iterator.next();
        iterator.remove();
      } else {
        throw new UnsupportedOperationException();
      }
    });
  }

}
