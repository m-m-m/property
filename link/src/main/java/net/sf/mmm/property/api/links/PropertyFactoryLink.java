/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.lang.reflect.Type;

import javax.inject.Named;

import net.sf.mmm.property.ReadableProperty;
import net.sf.mmm.property.WritableProperty;
import net.sf.mmm.property.factory.AbstractPropertyFactory;
import net.sf.mmm.property.factory.PropertyFactory;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This is the implementation of {@link PropertyFactory} for {@link LinkProperty}.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@Named
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PropertyFactoryLink<E extends Entity> extends AbstractPropertyFactory<Link<E>, LinkProperty<E>> {

  @Override
  public Class<Link<E>> getValueClass() {

    return (Class) Link.class;
  }

  @Override
  public Class<? extends ReadableProperty<Link<E>>> getReadableInterface() {

    return null;
  }

  @Override
  public Class<? extends WritableProperty<Link<E>>> getWritableInterface() {

    return null;
  }

  @Override
  public Class<LinkProperty<E>> getImplementationClass() {

    return (Class) LinkProperty.class;
  }

  @Override
  public LinkProperty<E> create(String name, Class<? extends Link<E>> valueClass, Type valueType,
      AbstractValidator<? super Link<E>> validator) {

    return new LinkProperty<>(name, validator);
  }

}