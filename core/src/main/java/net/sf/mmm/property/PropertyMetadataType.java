/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.sf.mmm.validation.Validator;
import net.sf.mmm.validation.ValidatorNone;

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

  private final Map<Class<?>, Object> annotations;

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
   * @param annotations the {@link #getAnnotations() annotations}.
   */
  public PropertyMetadataType(Validator<? super V> validator, Supplier<? extends V> expression, Object[] annotations) {

    this(validator, expression, null, toMap(annotations));
  }

  /**
   * The constructor.
   *
   * @param validator the {@link #getValidator() validator}.
   * @param supplier the {@link #getExpression() expression}.
   * @param valueType the {@link #getValueType() value type}.
   * @param annotations the {@link #getAnnotations() annotations}.
   */
  public PropertyMetadataType(Validator<? super V> validator, Supplier<? extends V> supplier, Type valueType,
      Map<Class<?>, Object> annotations) {

    super();
    this.validator = validator;
    this.expression = supplier;
    this.valueType = valueType;
    if (annotations == null) {
      this.annotations = Collections.emptyMap();
    } else {
      this.annotations = Collections.unmodifiableMap(annotations);
    }
  }

  private static Map<Class<?>, Object> toMap(Object[] annotations) {

    if ((annotations == null) || (annotations.length == 0)) {
      return null;
    }
    Map<Class<?>, Object> map = new HashMap<>(annotations.length);
    for (Object annotation : annotations) {
      Class<?> type;
      if (annotation instanceof Annotation) {
        type = ((Annotation) annotation).annotationType();
      } else {
        type = annotation.getClass();
      }
      map.put(type, annotation);
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

  @SuppressWarnings("unchecked")
  @Override
  public <A> A getAnnotation(Class<A> annotationType) {

    return (A) this.annotations.get(annotationType);
  }

  @Override
  public Iterable<?> getAnnotations() {

    return this.annotations.values();
  }

}
