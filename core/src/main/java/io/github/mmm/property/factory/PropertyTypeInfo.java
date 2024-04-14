package io.github.mmm.property.factory;

import java.lang.reflect.Type;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;

/**
 * Interface with the type information required for generic {@link WritableProperty property}
 * {@link PropertyFactory#create(String, PropertyTypeInfo, io.github.mmm.property.PropertyMetadata) creation}.
 *
 * @param <V> type of {@link #getValueClass()}.
 * @since 1.0.0
 */
public interface PropertyTypeInfo<V> {

  /**
   * @return the {@link Class} reflecting the {@link WritableProperty property} {@link WritableProperty#get() value}.
   */
  Class<V> getValueClass();

  /**
   * @return the optional {@link io.github.mmm.property.container.ContainerProperty#getValueProperty() value property}
   *         or {@code null}.
   */
  default WritableProperty<?> getValueProperty() {

    return null;
  }

  /**
   * @return the optional {@link io.github.mmm.property.container.map.MapProperty#getKeyProperty() key property} or
   *         {@code null}.
   */
  default WritableSimpleProperty<?> getKeyProperty() {

    return null;
  }

  /**
   * @param i the index position of the requested type parameters. Use {@code 0} for the first parameter.
   * @return the type parameter at the given {@code index}. Will be {@code null} if not available. If
   *         {@link #getValueClass()} returns {@code null} this will be the type parameters of the property type and
   *         otherwise of the value type. This method is provided for custom property developers who need generic
   *         information in they {@link io.github.mmm.property.factory.PropertyFactory} implementation. In order to draw
   *         the correct conclusions, also consider {@link #isValueNotProperty()}.
   */
  default Type getTypeArgument(int i) {

    return null;
  }

  /**
   * @param i the index position of the requested type parameters. Use {@code 0} for the first parameter.
   * @return the type parameter at the given {@code index}. Will be {@code null} if not available. If
   *         {@link #getValueClass()} returns {@code null} this will be the type parameters of the property type and
   *         otherwise of the value type. This method is provided for custom property developers who need generic
   *         information in they {@link io.github.mmm.property.factory.PropertyFactory} implementation. In order to draw
   *         the correct conclusions, also consider {@link #isValueNotProperty()}.
   */
  default Class<?> getTypeArgumentClass(int i) {

    return null;
  }

  /**
   * @return {@code true} if the {@link #getOwnerType() owner} is the {@link WritableProperty#get() property value} (see
   *         {@link #getValueClass()}), {@code false} in case of the {@link WritableProperty property} itself.
   * @see #getTypeArgument(int)
   * @see #getOwnerType()
   * @see #getOwnerClass()
   */
  default boolean isValueNotProperty() {

    return true;
  }

  /**
   * @return the {@link Type} this {@link PropertyTypeInfo} is derived from. According to {@link #isValueNotProperty()}
   *         this {@link Type} will either reflect the {@link WritableProperty#get() property value} or the
   *         {@link WritableProperty property} itself.
   * @see #isValueNotProperty()
   */
  default Type getOwnerType() {

    return getValueClass();
  }

  /**
   * @return the {@link Class} this {@link PropertyTypeInfo} is derived from. According to {@link #isValueNotProperty()}
   *         this {@link Class} will either reflect the {@link WritableProperty#get() property value} or the
   *         {@link WritableProperty property} itself.
   * @see #isValueNotProperty()
   */
  default Class<?> getOwnerClass() {

    return getValueClass();
  }

  /**
   * @param <V> type of {@link #getValueClass()}.
   * @param valueClass the {@link #getValueClass()}.
   * @return a simple {@link PropertyTypeInfo} only containing {@link #getValueClass()}.
   */
  static <V> PropertyTypeInfo<V> ofValueClass(Class<V> valueClass) {

    return () -> valueClass;
  }

}
