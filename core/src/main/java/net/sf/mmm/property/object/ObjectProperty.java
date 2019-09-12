/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.object;

import java.lang.reflect.Type;
import java.util.Objects;

import net.sf.mmm.property.Property;
import net.sf.mmm.property.PropertyMetadata;
import net.sf.mmm.property.PropertyMetadataNone;
import net.sf.mmm.property.WritableProperty;

/**
 * Generic implementation of {@link WritableProperty} for arbitrary objects that do not have their own custom
 * implementation.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class ObjectProperty<V> extends Property<V> implements WritableObjectProperty<V> {

  private final Class<V> valueClass;

  private final Type valueType;

  private V value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   */
  public ObjectProperty(String name, Class<V> valueClass) {

    this(name, valueClass, PropertyMetadataNone.getInstance());
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param valueClass the {@link #getValueClass() value class}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public ObjectProperty(String name, Class<V> valueClass, PropertyMetadata<V> metadata) {

    super(name, metadata);
    Objects.requireNonNull(valueClass);
    this.valueClass = valueClass;
    Type type = metadata.getValueType();
    if (type == null) {
      type = valueClass;
    }
    this.valueType = type;
  }

  @Override
  public Class<V> getValueClass() {

    return this.valueClass;
  }

  @Override
  public Type getValueType() {

    return this.valueType;
  }

  @Override
  protected V doGetValue() {

    return this.value;
  }

  @Override
  protected void doSetValue(V newValue) {

    assert (this.valueClass.isInstance(newValue));
    this.value = newValue;
  }

  // /**
  // * @return a new {@link ObjectValidatorBuilder builder} for the validator of this property with a
  // * {@link ObjectValidatorBuilder#and() parent-builder} to create a {@link #copy(AbstractValidator)} of this
  // * property with the configured validator.
  // */
  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // @Override
  // public ObjectValidatorBuilder<? extends V, ? extends PropertyBuilder<? extends ObjectProperty<? extends V>>, ?>
  // withValdidator() {
  //
  // return withValdidator(x -> new ValidatorBuilderObject(x));
  // }

}
