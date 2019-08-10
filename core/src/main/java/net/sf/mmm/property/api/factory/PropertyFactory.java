/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.factory;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the interface for the factory of a specific {@link ReadableProperty property} type. You can directly
 * instantiate implementations such as {@link net.sf.mmm.property.api.strings.StringProperty}. However for generic
 * support according types have to be registered via an implementation of this interface.
 *
 * @param <V> the generic type of the {@link ReadableProperty#getValue() property value}.
 * @param <P> the generic type of the {@link #getImplementationClass() property implementation}.
 *
 * @see PropertyFactoryManager
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface PropertyFactory<V, P extends ReadableProperty<V>> {

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
   * @param bean the {@link ReadableProperty#getBean() property bean}.
   * @param validator the {@link AbstractValidator validator} used for {@link WritableProperty#validate() validation}.
   *        May be {@code null}.
   *
   * @return the new instance of the property.
   */
  P create(String name, Class<? extends V> valueClass, Object bean, AbstractValidator<? super V> validator);

}
