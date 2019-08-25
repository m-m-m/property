/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.util.function.Supplier;

import net.sf.mmm.property.api.objects.ObjectProperty;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * This class represents a {@link ObjectProperty property} containing a {@link Link} that {@link Link#getTarget() points
 * to} an {@link Entity}.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LinkProperty<E extends Entity> extends ObjectProperty<Link<E>> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  public LinkProperty(String name, Object bean) {

    super(name, (Class) Link.class, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param type - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public LinkProperty(String name, Object bean, AbstractValidator<? super Link<E>> validator) {

    super(name, (Class) Link.class, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param type - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public LinkProperty(String name, Object bean, Supplier<? extends Link<E>> expression) {

    super(name, (Class) Link.class, bean, expression);
  }

}
