/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.function.Supplier;

import net.sf.mmm.validation.Validator;
import net.sf.mmm.validation.ValidatorNone;

/**
 * Implementation of {@link PropertyMetadata} that is entirely empty.
 *
 * @param <V> type of the {@link Property#getValue() property value}.
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

    return ValidatorNone.getInstance();
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
  public <A> A getAnnotation(Class<A> annotationType) {

    return null;
  }

  @Override
  public Iterable<?> getAnnotations() {

    return Collections.emptyList();
  }

  /**
   * @param <V> type of the {@link Property#getValue() property value}.
   * @return the singleton instance of this class.
   */
  @SuppressWarnings("unchecked")
  public static <V> PropertyMetadataNone<V> getInstance() {

    return (PropertyMetadataNone<V>) INSTANCE;
  }

}
