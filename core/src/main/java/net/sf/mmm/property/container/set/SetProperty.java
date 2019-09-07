/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.container.set;

import java.lang.reflect.Type;
import java.util.Set;

import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.container.collection.CollectionProperty;
import net.sf.mmm.value.observable.container.set.ChangeAwareSet;
import net.sf.mmm.value.observable.container.set.ChangeAwareSets;
import net.sf.mmm.value.observable.container.set.SetChangeListener;

/**
 * Implementation of {@link WritableSetProperty}.
 *
 * @param <E> type of the {@link Set#contains(Object) contained elements}.
 *
 * @since 1.0.0
 */
public class SetProperty<E> extends CollectionProperty<Set<E>, E> implements WritableSetProperty<E> {

  private final SetChangeListener<E> setChangeListener = change -> {
    invalidateProperties();
    fireChange(change);
  };

  private Set<E> value;

  private ChangeAwareSet<E> changeAwareSet;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   */
  public SetProperty(String name, Class<E> componentClass, Type componentType) {

    super(name, componentClass, componentType);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param componentClass the {@link #getComponentClass() component class}.
   * @param componentType the {@link #getComponentType() component type}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public SetProperty(String name, Class<E> componentClass, Type componentType, PropertyMetadata<Set<E>> metadata) {

    super(name, componentClass, componentType, metadata);
  }

  @Override
  protected Set<E> doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(Set<E> newValue) {

    this.value = newValue;
  }

  @Override
  public boolean isChangeAware() {

    return (this.changeAwareSet != null);
  }

  @Override
  public ChangeAwareSet<E> getChangeAwareValue() {

    if (this.changeAwareSet == null) {
      this.changeAwareSet = ChangeAwareSets.of(getOrCreateValue());
      this.changeAwareSet.addListener(this.setChangeListener);
    }
    return this.changeAwareSet;
  }

  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // @Override
  // public AbstractCollectionValidatorBuilder<E, Set<E>, PropertyBuilder<SetProperty<E>>, ?> withValdidator() {
  //
  // Function factory = new Function<PropertyBuilder<SetProperty<E>>, ValidatorBuilderCollection<E,
  // PropertyBuilder<SetProperty<E>>>>() {
  //
  // @Override
  // public ValidatorBuilderCollection<E, PropertyBuilder<SetProperty<E>>> apply(PropertyBuilder<SetProperty<E>> t) {
  //
  // return new ValidatorBuilderCollection<>(t);
  // }
  // };
  // return (ValidatorBuilderCollection) withValdidator(factory);
  // }

}
