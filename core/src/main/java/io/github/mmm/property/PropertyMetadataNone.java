/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.function.Supplier;

import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link PropertyMetadata} that is entirely empty.
 *
 * @param <V> type of the {@link Property#get() property value}.
 *
 * @since 1.0.0
 */
public final class PropertyMetadataNone<V> implements PropertyMetadata<V> {

  private static final PropertyMetadataNone<Object> INSTANCE = new PropertyMetadataNone<>();

  private PropertyMetadataNone() {

    super();
  }

  @Override
  public Validator<? super V> getValidator() {

    return Validator.none();
  }

  @Override
  public Supplier<? extends V> getExpression() {

    return null;
  }

  @Override
  public Type getValueType() {

    return null;
  }

  @Override
  public Object get(String key) {

    return null;
  }

  @Override
  public Iterable<String> getKeys() {

    return Collections.emptyList();
  }

  /**
   * @param <V> type of the {@link Property#get() property value}.
   * @return the singleton instance of this class.
   */
  @SuppressWarnings("unchecked")
  public static <V> PropertyMetadataNone<V> getInstance() {

    return (PropertyMetadataNone<V>) INSTANCE;
  }

}
