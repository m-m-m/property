/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.validation.Validator;

/**
 * Default implementation of {@link PropertyMetadataFactory}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataFactoryDefault implements PropertyMetadataFactory {

  static final PropertyMetadataFactoryDefault INSTANCE = new PropertyMetadataFactoryDefault();

  @Override
  public <V> PropertyMetadata<V> create(Validator<? super V> validator, Supplier<? extends V> expression,
      Type valueType, Map<String, Object> map) {

    if (Validator.isValidating(validator) || (expression != null) || (valueType != null) || (map != null)) {
      return new PropertyMetadataType<>(validator, expression, valueType, map);
    }
    return PropertyMetadataNone.get();
  }

}
