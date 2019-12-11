/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.PropertyMetadataNone;
import io.github.mmm.property.PropertyMetadataType;
import io.github.mmm.property.builder.container.ListPropertyBuilder;
import io.github.mmm.property.builder.container.MapPropertyBuilder;
import io.github.mmm.property.builder.container.SetPropertyBuilder;
import io.github.mmm.property.object.SimpleProperty;
import io.github.mmm.validation.Validator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * Abstract base class for a {@link io.github.mmm.base.lang.Builder} to create instances of a particular type of
 * {@link Property}. <br>
 * As a {@link Property} typically has only one single required parameter that is also the most specific parameter, it
 * is provided to the {@link #build(String) build-method}. Therefore {@link ProcessBuilder} does not implement
 * {@link io.github.mmm.base.lang.Builder} and does not follow the default builder-pattern providing a non-arg
 * {@link io.github.mmm.base.lang.Builder#build() build()} method.<br>
 * For a specific {@link Property} type the builder should carry the same name with the suffix {@code Builder}. Example:
 * {@link io.github.mmm.property.booleans.BooleanProperty} is build by
 * {@link io.github.mmm.property.builder.lang.BooleanPropertyBuilder}.
 *
 * @param <V> type of the {@link Property#get() property value}.
 * @param <P> type of the {@link Property} to build.
 * @param <B> type of the {@link ObjectValidatorBuilder validator builder} for {@link #withValidator()}.
 * @param <SELF> type of this {@link PropertyBuilder} itself.
 *
 * @since 1.0.0
 */
public abstract class PropertyBuilder<V, P extends Property<V>, B extends ObjectValidatorBuilder<V, ? extends PropertyBuilder<V, P, B, SELF>, ?>, SELF extends PropertyBuilder<V, P, B, SELF>> {

  private B validatorBuilder;

  /** @see #valueExpression(Supplier) */
  protected Supplier<? extends V> expression;

  /** @see #value(Object) */
  protected V value;

  /** @see PropertyMetadata#getValueType() */
  protected Type valueType;

  /** @see #metadata(String, Object) */
  protected Map<String, Object> metadataMap;

  /**
   * The constructor.
   */
  public PropertyBuilder() {

    super();
  }

  /**
   * @return this object itself ({@code this}) for fluent API calls.
   */
  @SuppressWarnings("unchecked")
  protected SELF self() {

    return (SELF) this;
  }

  /**
   * @param propertyValueExpression the {@link io.github.mmm.property.PropertyMetadata#getExpression() property value
   *        expression}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SELF valueExpression(Supplier<? extends V> propertyValueExpression) {

    this.expression = propertyValueExpression;
    return self();
  }

  /**
   * @param initialValue the {@link Property#get() initial value} of the {@link Property} to build.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SELF value(V initialValue) {

    this.value = initialValue;
    return self();
  }

  /**
   * @param metadataKey the {@link PropertyMetadata#get(String) metadata key}.
   * @param metadataValue the {@link PropertyMetadata#get(String) metadata value}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SELF metadata(String metadataKey, Object metadataValue) {

    if (this.metadataMap == null) {
      this.metadataMap = new HashMap<>();
    }
    this.metadataMap.put(metadataKey, metadataValue);
    return self();
  }

  /**
   * @return the {@link ObjectValidatorBuilder} corresponding to this type of builder.
   */
  protected abstract B createValidatorBuilder();

  /**
   * @return a sub-builder for the {@link PropertyMetadata#getValidator() validator} of the {@link Property} to build.
   *         Use the {@link ObjectValidatorBuilder#and() and()} method to return from the sub-builder to this property
   *         builder when the validator is configured.
   */
  public B withValidator() {

    if (this.validatorBuilder == null) {
      this.validatorBuilder = createValidatorBuilder();
    }
    return this.validatorBuilder;
  }

  /**
   * @param name the {@link Property#getName() property name} of the {@link Property} to build.
   * @return the {@link Property} to build.
   */
  public P build(String name) {

    Validator<? super V> validator = null;
    if (this.validatorBuilder != null) {
      validator = this.validatorBuilder.build();
    }
    PropertyMetadata<V> metadata;
    if ((validator == null) && (this.expression == null) && (this.valueType == null) && (this.metadataMap == null)) {
      metadata = PropertyMetadataNone.getInstance();
    } else {
      metadata = new PropertyMetadataType<>(validator, this.expression, this.valueType, this.metadataMap);
    }
    P property = build(name, metadata);
    if (this.value != null) {
      property.set(this.value);
    }
    return property;
  }

  /**
   * @param name the {@link Property#getName() property name} of the {@link Property} to build.
   * @param metadata the {@link PropertyMetadata}.
   * @return the {@link Property} to {@link #build(String) build}.
   */
  protected abstract P build(String name, PropertyMetadata<V> metadata);

  /**
   * @return a {@link ListPropertyBuilder} using {@link #build(String) this property configuration} for its elements.
   */
  public ListPropertyBuilder<V> asList() {

    return new ListPropertyBuilder<>(build("Value"));
  }

  /**
   * @return a {@link SetPropertyBuilder} using {@link #build(String) this property configuration} for its elements.
   */
  public SetPropertyBuilder<V> asSet() {

    return new SetPropertyBuilder<>(build("Value"));
  }

  /**
   * @return a {@link MapPropertyBuilder} using {@link #build(String) this property configuration} for its values and
   *         {@link String} as keys.
   */
  public MapPropertyBuilder<String, V> asMap() {

    return new MapPropertyBuilder<>(build("Value"));
  }

  /**
   * @param <K> type of the {@link Map#containsKey(Object) keys}.
   * @param keyProperty the {@link io.github.mmm.property.container.map.MapProperty#getKeyProperty() key property}.
   * @return a {@link MapPropertyBuilder} using {@link #build(String) this property configuration} for its values and
   *         {@link String} as keys.
   */
  public <K> MapPropertyBuilder<K, V> asMap(SimpleProperty<K> keyProperty) {

    return new MapPropertyBuilder<>(keyProperty, build("Value"));
  }

}
