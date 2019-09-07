/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property;

import java.lang.reflect.Type;
import java.util.function.Supplier;

import net.sf.mmm.validation.Validator;

/**
 * Metadata of a {@link Property}. It is discouraged and may have odd effects if the {@link PropertyMetadata} changes
 * after it has been passed to the constructor of a property.
 *
 * @param <V> type of the {@link Property#getValue() property value}.
 *
 * @since 1.0.0
 */
public interface PropertyMetadata<V> {

  /**
   * @return the {@link Validator} used to {@link ReadableProperty#validate() validate} the property.
   */
  Validator<? super V> getValidator();

  /**
   * @return the optional {@link Supplier} that calculates the {@link ReadableProperty#getValue() value} dynamically.
   *         May be {@code null} for a regular property. If it is not {@code null} the property will automatically be
   *         {@link ReadableProperty#isReadOnly() read-only}. You should not provide an explicit {@link #getValidator()
   *         validator} if you provide an expression.
   */
  Supplier<? extends V> getExpression();

  /**
   * @param <A> type of the annotation.
   * @param annotationType the {@link Class} reflecting the requested annotation. Here an annotation may be any kind of
   *        object. It may but does not have to be a {@link java.lang.annotation.Annotation}.
   * @return the requested annotation or {@code null} if no such annotation is present.
   */
  <A> A getAnnotation(Class<A> annotationType);

  /**
   * @return the annotations of the property.
   */
  Iterable<?> getAnnotations();

  /**
   * @return the optional {@link Type} of the {@link Property#getValue() property value}. May be {@code null}.
   * @see ReadableProperty#getValueClass()
   */
  Type getValueType();

}
