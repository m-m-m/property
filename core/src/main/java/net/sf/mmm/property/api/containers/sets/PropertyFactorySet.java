/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.sets;

import java.lang.reflect.Type;
import java.util.Set;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * Implementation of {@link PropertyFactory} for {@link SetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
@Named
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactorySet<E> extends AbstractPropertyFactory<Set<E>, SetProperty<E>> {

  @Override
  public Class<? extends Set<E>> getValueClass() {

    return (Class) Set.class;
  }

  @Override
  public Class<? extends ReadableProperty<Set<E>>> getReadableInterface() {

    return (Class) ReadableSetProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<Set<E>>> getWritableInterface() {

    return (Class) WritableSetProperty.class;
  }

  @Override
  public Class<SetProperty<E>> getImplementationClass() {

    return (Class) SetProperty.class;
  }

  @Override
  public SetProperty<E> create(String name, Class<? extends Set<E>> valueClass, Object bean,
      AbstractValidator<? super Set<E>> validator) {

    Class<E> componentClass = null;
    Type componentType = null;
    return new SetProperty<>(name, componentClass, componentType, bean, validator);
  }

}
