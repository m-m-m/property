/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Supplier;

import net.sf.mmm.property.container.list.ListProperty;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * {@link ListProperty} with {@link List} of {@link Link}s that each {@link Link#getTarget() point to} an
 * {@link Entity}.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LinkListProperty<E extends Entity> extends ListProperty<Link<E>> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkListProperty(String name, Type componentType, Object bean) {

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
  public LinkListProperty(String name, Type componentType, Object bean,
      AbstractValidator<? super List<Link<E>>> validator) {

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
  public LinkListProperty(String name, Type componentType, Object bean, Supplier<List<Link<E>>> expression) {

    super(name, (Class) Link.class, componentType, expression);
  }

}
