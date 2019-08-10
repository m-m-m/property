/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.objects;

import javax.inject.Named;

import net.sf.mmm.property.api.ReadableProperty;
import net.sf.mmm.property.api.WritableProperty;
import net.sf.mmm.property.api.factory.AbstractPropertyFactory;
import net.sf.mmm.property.api.factory.PropertyFactory;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link ObjectProperty}.
 *
 * @param <V> is the generic type of the {@link ObjectProperty#getValue() value}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyFactoryObject<V> extends AbstractPropertyFactory<V, ObjectProperty<V>> {

  @Override
  public Class<? extends V> getValueClass() {

    return null;
  }

  @Override
  public Class<? extends ReadableProperty<V>> getReadableInterface() {

    return (Class) ReadableProperty.class;
  }

  @Override
  public Class<? extends WritableProperty<V>> getWritableInterface() {

    return (Class) WritableProperty.class;
  }

  @Override
  public Class<ObjectProperty<V>> getImplementationClass() {

    return (Class) ObjectProperty.class;
  }

  @Override
  public ObjectProperty<V> create(String name, Class<? extends V> valueType, Object bean,
      AbstractValidator<? super V> validator) {

    return new ObjectProperty(name, valueType, bean, validator);
  }

}
