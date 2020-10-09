/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.validation.Validator;

/**
 * Factory to {@link #create(Validator, Supplier, Type, Map) create} instances of {@link PropertyMetadata}.
 *
 * @since 1.0.0
 */
public interface PropertyMetadataFactory {

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param validator the {@link PropertyMetadata#getValidator() validator}.
   * @param expression the {@link PropertyMetadata#getExpression() expression}.
   * @param valueType the {@link PropertyMetadata#getValueType() value type}.
   * @param map the {@link PropertyMetadata#get(String) metadata} {@link Map}.
   * @return the new {@link PropertyMetadata}.
   */
  <V> PropertyMetadata<V> create(Validator<? super V> validator, Supplier<? extends V> expression, Type valueType,
      Map<String, Object> map);

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @param metadata the existing {@link PropertyMetadata} to copy or convert.
   * @return the new {@link PropertyMetadata}.
   */
  default <V> PropertyMetadata<V> create(PropertyMetadata<V> metadata) {

    if (metadata == null) {
      return create(null, null, null, null);
    }
    return create(metadata.getValidator(), metadata.getExpression(), metadata.getValueType(), metadata.asMap());
  }

  /**
   * @return the default instance.
   */
  static PropertyMetadataFactory get() {

    return PropertyMetadataFactoryDefault.INSTANCE;
  }

  /**
   * @param factory the optional {@link PropertyMetadataFactory}.
   * @return the given {@link PropertyMetadataFactory} if not {@code null} or the {@link #get() default instance}
   *         otherwise.
   */
  static PropertyMetadataFactory get(PropertyMetadataFactory factory) {

    if (factory != null) {
      return factory;
    }
    return PropertyMetadataFactoryDefault.INSTANCE;
  }

}
