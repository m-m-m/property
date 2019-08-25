/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.containers.lists;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.property.api.containers.ChangeAwareContainerProperty;
import net.sf.mmm.util.validation.base.AbstractValidator;
import net.sf.mmm.util.validation.base.collection.AbstractCollectionValidatorBuilder;
import net.sf.mmm.util.validation.base.collection.ValidatorBuilderCollection;
import net.sf.mmm.value.observable.containers.lists.ChangeAwareList;
import net.sf.mmm.value.observable.containers.lists.ChangeAwareLists;
import net.sf.mmm.value.observable.containers.lists.ListChangeListener;

/**
 * This is the implementation of {@link WritableListProperty}.
 *
 * @param <E> the generic type of the {@link List#get(int) elements} of the {@link List}.
 *
 * @author hohwille
 * @since 1.0.0
 */
@SuppressWarnings("restriction")
public class ChangeAwareListProperty<E> extends ChangeAwareContainerProperty<List<E>, E>
    implements WritableChangeAwareListProperty<E> {

  private final ListChangeListener<E> listChangeListener = change -> {
    invalidateProperties();
    fireChange(change);
  };

  private ChangeAwareList<E> value;

  private List<E> originalValue;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   */
  public ChangeAwareListProperty(String name, Class<E> componentClass) {

    this(name, componentClass, componentClass, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public ChangeAwareListProperty(String name, Class<E> componentClass, Type componentType) {

    this(name, componentClass, componentType, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   */
  public ChangeAwareListProperty(String name, Class<E> componentClass, Type componentType, Object bean) {

    super(name, componentClass, componentType, bean);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param validator - see {@link #validate()}.
   */
  public ChangeAwareListProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      AbstractValidator<? super List<E>> validator) {

    super(name, componentClass, componentType, bean, validator);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param bean the {@link #getBean() bean}.
   * @param expression the {@link Supplier} {@link Supplier#get() providing} the actual {@link #getValue() value}.
   */
  public ChangeAwareListProperty(String name, Class<E> componentClass, Type componentType, Object bean,
      Supplier<List<E>> expression) {

    super(name, componentClass, componentType, bean, expression);
  }

  @Override
  public List<E> getOriginalValue() {

    return this.originalValue;
  }

  @Override
  public ChangeAwareList<E> getValue() {

    return (ChangeAwareList<E>) super.getValue();
  }

  @Override
  protected ChangeAwareList<E> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(List<E> newValue) {

    if (this.value != null) {
      this.value.removeListener(this.listChangeListener);
    }
    if (newValue == null) {
      this.value = null;
    } else if (newValue instanceof ChangeAwareList) {
      this.value = (ChangeAwareList<E>) newValue;
    } else {
      this.value = ChangeAwareLists.of(newValue);
    }
    this.originalValue = newValue;
    if (this.value != null) {
      this.value.addListener(this.listChangeListener);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public AbstractCollectionValidatorBuilder<E, List<E>, PropertyBuilder<ChangeAwareListProperty<E>>, ?> withValdidator() {

    Function factory = new Function<PropertyBuilder<ChangeAwareListProperty<E>>, ValidatorBuilderCollection<E, PropertyBuilder<ChangeAwareListProperty<E>>>>() {

      @Override
      public ValidatorBuilderCollection<E, PropertyBuilder<ChangeAwareListProperty<E>>> apply(
          PropertyBuilder<ChangeAwareListProperty<E>> t) {

        return new ValidatorBuilderCollection<>(t);
      }
    };
    return (ValidatorBuilderCollection) withValdidator(factory);
  }

  @Override
  public void read(StructuredReader reader) {

    List<E> list = getValue();
    if (list != null) {
      list.clear();
    }
    if (!reader.readStartArray()) {
      return;
    }
    if (list == null) {
      list = getOrCreateValue();
    }
    Class<E> componentClass = getComponentClass();
    do {
      E element = reader.readValue(componentClass);
      list.add(element);
    } while (!reader.readEnd());
  }

  @Override
  public void write(StructuredWriter writer) {

    List<E> list = getValue();
    if ((list != null) && !list.isEmpty()) {
      writer.writeStartArray();
      for (E element : list) {
        writer.writeValue(element);
      }
      writer.writeEnd();
    }
  }

}
