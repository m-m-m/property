/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.function.Supplier;

import net.sf.mmm.property.container.set.SetProperty;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * {@link SetProperty} with {@link Set} {@link #getValue() value} containing {@link Link}s that each
 * {@link Link#getTarget() point to} an {@link Entity}. Unlike {@link LinkListProperty} the {@link Link}s of an
 * {@link LinkSetProperty} have no order and can not contain duplicates.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @since 1.0.0
 */
public class LinkSetProperty<E extends Entity> extends SetProperty<Link<E>> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkSetProperty(String name, Type componentType, Object bean) {

    super(name, (Class) Link.class, componentType);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param validator - see {@link #validate()}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkSetProperty(String name, Type componentType, Object bean,
      AbstractValidator<? super Set<Link<E>>> validator) {

    super(name, (Class) Link.class, componentType);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkSetProperty(String name, Type componentType, Object bean, Supplier<Set<Link<E>>> expression) {

    super(name, (Class) Link.class, componentType, expression);
  }

}
