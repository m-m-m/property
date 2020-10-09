/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.validation.Validator;

/**
 * Abstract base implementation of {@link PropertyMetadata}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 * @since 1.0.0
 */
public abstract class AbstractPropertyMetadata<V> implements PropertyMetadata<V> {

  /**
   * The constructor.
   */
  public AbstractPropertyMetadata() {

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

    return asMap().get(key);
  }

  @Override
  public Iterable<String> getKeys() {

    return asMap().keySet();
  }

  @Override
  public int getKeyCount() {

    return asMap().size();
  }

  @Override
  public Map<String, Object> asMap() {

    return Collections.emptyMap();
  }

}
