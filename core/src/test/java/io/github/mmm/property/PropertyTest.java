package io.github.mmm.property;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.mmm.base.exception.ReadOnlyException;
import io.github.mmm.property.factory.PropertyFactoryManager;

/**
 * Abstract test case for testing sub-classes of {@link Property}.
 *
 * @param <V> type of the {@link Property#get() value}.
 * @param <P> type of the {@link Property}.
 */
public abstract class PropertyTest<V, P extends Property<V>> extends Assertions {

  /**
   * @return the {@link Property#getValueClass() value class}.
   */
  protected abstract Class<V> getValueClass();

  /**
   * @return an example value that is not {@code null}.
   */
  protected abstract V getExampleValue();

  /** Test via {@link PropertyFactoryManager#create(Class, String)}. */
  @Test
  public void testFactory() {

    // given
    Class<V> valueClass = getValueClass();
    String name = "name123";
    V example = getExampleValue();
    // when
    WritableProperty<V> property = PropertyFactoryManager.get().create(valueClass, name);
    // then
    assertThat(property.getName()).isEqualTo(name);
    assertThat(property.getValueClass()).isSameAs(valueClass);
    assertThat(property.getSafe()).isNotNull();
    assertThat(property.get()).isNull();
    property.set(example);
    assertThat(property.get()).isSameAs(example);
    assertThat(property.isReadOnly()).isEqualTo(false);
    WritableProperty<V> readOnly = property.getReadOnly();
    assertThat(readOnly.isReadOnly()).isEqualTo(true);
    assertThat(readOnly.get()).isSameAs(example);
    assertThrows(ReadOnlyException.class, () -> {
      readOnly.set(null);
    });
  }

}
