/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.util.function.Supplier;

import io.github.mmm.base.metainfo.MetaInfo;
import io.github.mmm.marshall.Marshalling;
import io.github.mmm.property.impl.metadata.PropertyMetadataExpression;
import io.github.mmm.property.impl.metadata.PropertyMetadataLock;
import io.github.mmm.property.impl.metadata.PropertyMetadataInfo;
import io.github.mmm.property.impl.metadata.PropertyMetadataNone;
import io.github.mmm.property.impl.metadata.PropertyMetadataValidator;
import io.github.mmm.property.impl.readonly.AttributeNeverReadOnly;
import io.github.mmm.validation.Validator;

/**
 * Metadata of a {@link Property}. Implementations of {@link PropertyMetadata} are supposed to be immutable. It is
 * discouraged and may have odd effects if the {@link PropertyMetadata} changes after it has been passed to the
 * constructor of a property.
 *
 * @param <V> type of the {@link Property#get() property value}.
 * @since 1.0.0
 */
public interface PropertyMetadata<V> {

  /**
   * @return the {@link Validator} used to {@link ReadableProperty#validate() validate} the property.
   */
  Validator<? super V> getValidator();

  /**
   * @return the optional {@link Supplier} that calculates the {@link ReadableProperty#get() value} dynamically. May be
   *         {@code null} for a regular property. If it is not {@code null} the property will automatically be
   *         {@link ReadableProperty#isReadOnly() read-only}. You should not provide an explicit {@link #getValidator()
   *         validator} if you provide an expression.
   */
  Supplier<? extends V> getExpression();

  /**
   * @return the optional custom {@link Marshalling}. If {@code null} the default marshalling of the {@link Property} is
   *         used. Overriding also allows to extend or replace the property value with more complex data from this
   *         metadata - e.g. to reuse a {@code Bean} to represent a query to find instances of that {@code Bean}.
   */
  default Marshalling<V> getMarshalling() {

    return null;
  }

  /**
   * @return the actual meta-info.
   */
  default MetaInfo getMetaInfo() {

    return MetaInfo.empty();
  }

  /**
   * @return {@code true} if transient (e.g. computed and therefore not to be marshalled), {@code false} otherwise.
   * @see ReadableProperty#isTransient()
   */
  default boolean isTransient() {

    return (getExpression() != null);
  }

  /**
   * @param newLock the new {@link #getLock() lock}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link AttributeReadOnly} used for
   *         {@link #getLock()}.
   */
  PropertyMetadata<V> withLock(AttributeReadOnly newLock);

  /**
   * @param newValidator the new {@link Validator}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link Validator} used for
   *         {@link #getValidator()}.
   */
  PropertyMetadata<V> withValidator(Validator<? super V> newValidator);

  /**
   * @param newExpression the new {@link #getExpression() expression}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link Supplier} used for
   *         {@link #getExpression()}.
   */
  PropertyMetadata<V> withExpression(Supplier<? extends V> newExpression);

  /**
   * @param metaInfo the new {@link #getMetaInfo() meta-info}.
   * @return a new instance of {@link PropertyMetadata} with the given {@link MetaInfo} used for {@link #getMetaInfo()}.
   */
  PropertyMetadata<V> withMetaInfo(MetaInfo metaInfo);

  /**
   * @return the lock object that can make the owning {@link Property} {@link AttributeReadOnly#isReadOnly() read-only}.
   *         Typically this is the owning bean.
   */
  default AttributeReadOnly getLock() {

    return AttributeNeverReadOnly.INSTANCE;
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link #getLock() lock} (owning bean).
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   * @param metaInfo the {@link #getMetaInfo() meta-info}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression, MetaInfo metaInfo) {

    if (isNotEmpty(metaInfo)) {
      return new PropertyMetadataInfo<>(lock, validator, expression, metaInfo);
    } else if (expression != null) {
      return new PropertyMetadataExpression<>(lock, validator, expression);
    } else if (Validator.isValidating(validator)) {
      return new PropertyMetadataValidator<>(lock, validator);
    } else if (lock != null) {
      return new PropertyMetadataLock<>(lock);
    }
    return PropertyMetadataNone.get();
  }

  private static boolean isNotEmpty(MetaInfo metaInfo) {

    if (metaInfo == null) {
      return false;
    }
    return !metaInfo.isEmpty();
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock) {

    return of(lock, null, null, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator) {

    return of(lock, validator, null, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> of(AttributeReadOnly lock, Validator<? super V> validator,
      Supplier<? extends V> expression) {

    return of(lock, validator, expression, null);
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param lock the {@link PropertyMetadata#getLock() lock} (owning bean).
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @return the new {@link PropertyMetadata}.
   */
  static <V> PropertyMetadata<V> ofExpression(Supplier<? extends V> expression) {

    return of(null, null, expression, null);
  }

}
