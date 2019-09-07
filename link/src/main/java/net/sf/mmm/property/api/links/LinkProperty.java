/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.util.function.Supplier;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.property.object.ObjectProperty;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.validation.base.AbstractValidator;

/**
 * {@link ObjectProperty} with {@link Link} {@link #getValue() value} that {@link Link#getTarget() points to} an
 * {@link Entity}.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @since 1.0.0
 */
public class LinkProperty<E extends Entity> extends ObjectProperty<Link<E>> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkProperty(String name, Object bean) {

    super(name, (Class) Link.class, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkProperty(String name, Object bean, AbstractValidator<? super Link<E>> validator) {

    super(name, (Class) Link.class, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public LinkProperty(String name, Object bean, Supplier<? extends Link<E>> expression) {

    super(name, (Class) Link.class, expression);
  }

  @Override
  public void read(StructuredReader reader) {

    // TODO Auto-generated method stub
    super.read(reader);
  }

  @Override
  public void write(StructuredWriter writer) {

    // TODO Auto-generated method stub
    super.write(writer);
  }

}
