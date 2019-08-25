/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links;

import java.util.Set;
import java.util.function.Supplier;

import net.sf.mmm.property.api.containers.sets.SetProperty;
import net.sf.mmm.property.api.lang.GenericProperty;
import net.sf.mmm.util.data.api.entity.Entity;
import net.sf.mmm.util.data.api.link.Link;
import net.sf.mmm.util.reflect.api.GenericType;
import net.sf.mmm.util.validation.base.AbstractValidator;

import javafx.collections.ObservableSet;

/**
 * This class represents a {@link GenericProperty property} containing a {@link Set} of {@link Link}s that each
 * {@link Link#getTarget() point to} an {@link Entity}. Unlike {@link LinkListProperty} the {@link Link}s of an
 * {@link LinkSetProperty} have no order and can not contain duplicates.
 *
 * @param <E> the generic type of the {@link Link#getTarget() linked} {@link Entity}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public class LinkSetProperty<E extends Entity> extends SetProperty<Link<E>> {

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param type - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   */
  public LinkSetProperty(String name, GenericType<ObservableSet<Link<E>>> type, Object bean) {

    super(name, type, bean);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param type - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param validator - see {@link #validate()}.
   */
  public LinkSetProperty(String name, GenericType<ObservableSet<Link<E>>> type, Object bean,
      AbstractValidator<? super ObservableSet<Link<E>>> validator) {

    super(name, type, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name - see {@link #getName()}.
   * @param type - see {@link #getValueType()}.
   * @param bean - see {@link #getBean()}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public LinkSetProperty(String name, Object bean, Supplier<Set<Link<E>>> expression) {

    super(name, bean, expression);
  }

}
