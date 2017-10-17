/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.impl.factory;

import javax.inject.Named;

import javafx.collections.ObservableList;
import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.property.api.util.ListProperty;
import net.sf.mmm.property.api.util.ReadableListProperty;
import net.sf.mmm.property.api.util.WritableListProperty;
import net.sf.mmm.util.reflect.api.GenericType;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ListProperty}.
 *
 * @param <E> the generic type of the {@link ObservableList#get(int) list elements}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryList<E> extends AbstractPropertyFactory<ObservableList<E>, ListProperty<E>> {

  @Override
  public Class<? extends ObservableList<E>> getValueClass() {

    return (Class) ObservableList.class;
  }

  @Override
  public Class<? extends ReadableProperty<ObservableList<E>>> getReadableInterface() {

    return (Class) ReadableListProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<ObservableList<E>>> getWritableInterface() {

    return (Class) WritableListProperty.class;
  }

  @Override
  public Class<ListProperty<E>> getImplementationClass() {

    return (Class) ListProperty.class;
  }

  @Override
  public ListProperty<E> create(String name, GenericType<? extends ObservableList<E>> valueType, Object bean,
      AbstractValidator<? super ObservableList<E>> validator) {

    return new ListProperty<>(name, valueType, bean, validator);
  }

}
