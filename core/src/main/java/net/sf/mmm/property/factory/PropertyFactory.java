/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.factory;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;

/**
 * This is the interface for the factory of a specific {@link WritableProperty property} type. You can directly
 * instantiate implementations such as {@link net.sf.mmm.property.string.StringProperty}. However for generic support
 * according types have to be registered via an implementation of this interface.
 *
 * @param <V> the generic type of the {@link WritableProperty#getValue() property value}.
 * @param <P> the generic type of the {@link #getImplementationClass() property implementation}.
 *
 * @see PropertyFactoryManager
 *
 * @since 1.0.0
 */
public interface PropertyFactory<V, P extends WritableProperty<V>> {

  /**
   * @return the {@link Class} of the {@link ReadableProperty#getValue() property value}. May be {@code null} for a
   *         generic property.
   */
  Class<? extends V> getValueClass();

  /**
   * @return the {@link Class} reflecting the {@link ReadableProperty} interface. May be {@code null} if no dedicated
   *         readable interface exists.
   */
  Class<? extends ReadableProperty<V>> getReadableInterface();

  /**
   * @return the {@link Class} reflecting the {@link WritableProperty} interface. May be {@code null} if no dedicated
   *         writable interface exists.
   */
  Class<? extends WritableProperty<V>> getWritableInterface();

  /**
   * @return the {@link Class} reflecting the {@link WritableProperty} implementation.
   */
  Class<P> getImplementationClass();

  /**
   * Creates a new instance of the property.
   *
   * @param name the {@link ReadableProperty#getName() property name}.
   * @param valueClass the {@link ReadableProperty#getValueClass() value class}.
   * @param metadata TODO
   * @return the new instance of the property.
   */
  P create(String name, Class<? extends V> valueClass, PropertyMetadata<V> metadata);

  /**
   * @return {@code true} if {@link #getValueClass() value class} is polymorphic and also sub-types are handled by this
   *         {@link PropertyFactory} (unless there is a more specific {@link PropertyFactory} available), {@code false}
   *         otherwise.
   */
  default boolean isPolymorphic() {

    return false;
  }

}
