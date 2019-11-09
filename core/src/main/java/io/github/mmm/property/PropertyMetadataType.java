/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.validation.Validator;
import io.github.mmm.validation.ValidatorNone;

/**
 * Implementation of {@link PropertyMetadata} as immutable type.
 *
 * @param <V> type of the {@link Property#getValue() property value}.
 *
 * @since 1.0.0
 */
public class PropertyMetadataType<V> implements PropertyMetadata<V> {

  private final Validator<? super V> validator;

  private final Supplier<? extends V> expression;

  private final Type valueType;

  private final Map<String, Object> metadata;

  /**
   * The constructor.
   *
   * @param validator the {@link #getValidator() validator}.
   */
  public PropertyMetadataType(Validator<? super V> validator) {

    this(validator, null, null, Collections.emptyMap());
  }

  /**
   * The constructor.
   *
   * @param supplier the {@link #getExpression() supplier}.
   */
  public PropertyMetadataType(Supplier<? extends V> supplier) {

    this(ValidatorNone.getInstance(), supplier, null, Collections.emptyMap());
  }

  /**
   * The constructor.
   *
   * @param validator the {@link #getValidator() validator}.
   * @param expression the {@link #getExpression() expression}.
   * @param metadataValues the {@link #get(String) metadata values}. Should only be specific types such as
   *        {@link java.lang.annotation.Annotation}s-
   */
  public PropertyMetadataType(Validator<? super V> validator, Supplier<? extends V> expression,
      Object[] metadataValues) {

    this(validator, expression, null, toMap(metadataValues));
  }

  /**
   * The constructor.
   *
   * @param validator the {@link #getValidator() validator}.
   * @param supplier the {@link #getExpression() expression}.
   * @param valueType the {@link #getValueType() value type}.
   * @param metadata the {@link #get(String) metadata}.
   */
  public PropertyMetadataType(Validator<? super V> validator, Supplier<? extends V> supplier, Type valueType,
      Map<String, Object> metadata) {

    super();
    this.validator = validator;
    this.expression = supplier;
    this.valueType = valueType;
    if (metadata == null) {
      this.metadata = Collections.emptyMap();
    } else {
      this.metadata = Collections.unmodifiableMap(metadata);
    }
  }

  private static Map<String, Object> toMap(Object[] annotations) {

    if ((annotations == null) || (annotations.length == 0)) {
      return null;
    }
    Map<String, Object> map = new HashMap<>(annotations.length);
    for (Object annotation : annotations) {
      Class<?> type;
      if (annotation instanceof Annotation) {
        type = ((Annotation) annotation).annotationType();
      } else {
        type = annotation.getClass();
      }
      map.put(type.getName(), annotation);
    }
    return map;
  }

  @Override
  public Validator<? super V> getValidator() {

    return this.validator;
  }

  @Override
  public Supplier<? extends V> getExpression() {

    return this.expression;
  }

  @Override
  public Type getValueType() {

    return this.valueType;
  }

  @Override
  public Object get(String key) {

    return this.metadata.get(key);
  }

  @Override
  public Iterable<String> getKeys() {

    return this.metadata.keySet();
  }

}
