/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
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

  /** @see #getParent() */
  protected final PropertyBuilders parent;

  private Consumer<? super P> registry;

  private Function<String, P> factory;

  private B validatorBuilder;

  /** @see #valueExpression(Supplier) */
  protected Supplier<? extends V> expression;

  /** @see #value(Object) */
  protected V value;

  private Map<String, Object> metadataMap;

  /**
   * The constructor.
   *
   * @param parent the {@link PropertyBuilders}.
   */
  public PropertyBuilder(PropertyBuilders parent) {

    super();
    this.parent = parent;
  }

  /**
   * @return this object itself ({@code this}) for fluent API calls.
   */
  @SuppressWarnings("unchecked")
  protected SELF self() {

    return (SELF) this;
  }

  /**
   * @return the parent {@link PropertyBuilders} that created this property builder.
   */
  public PropertyBuilders getParent() {

    return this.parent;
  }

  /**
   * @param consumer the {@link Consumer} {@link Consumer#accept(Object) receiving} the {@link Property} when
   *        {@link #build(String) build}.
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SELF registry(Consumer<? super P> consumer) {

    if (this.registry != null) {
      throw new IllegalStateException("Registry consumer already set!");
    }
    this.registry = consumer;
    return self();
  }

  /**
   * @param function the {@link Function} optionally {@link Function#apply(Object) providing} the {@link Property} to
   *        {@link #build(String) build}. If it returns a result other than {@code null} this will be used instead and
   *        the actual building is entirely replaced. This is a very specific feature provided for internal usage (to
   *        receive read-only view of already existing property).
   * @return this builder itself ({@code this}) for fluent API calls.
   */
  public SELF factory(Function<String, P> function) {

    if (this.factory != null) {
      throw new IllegalStateException("Factory function already set!");
    }
    this.factory = function;
    return self();
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
  public final P build(String name) {

    return build(name, false);
  }

  /**
   * @param name the {@link Property#getName() property name} of the {@link Property} to build.
   * @param ignoreExtensions - {@code true} to ignore a potential {@link #registry(Consumer)} and
   *        {@link #factory(Function)}, {@code false} otherwise.
   * @return the {@link Property} to build.
   */
  protected final P build(String name, boolean ignoreExtensions) {

    P property = null;
    if ((this.factory != null) && !ignoreExtensions) {
      property = this.factory.apply(name);
    }
    if (property == null) {
      property = build(name, newMetadata());
      if (this.value != null) {
        property.set(this.value);
      }
    }
    if ((this.registry != null) && !ignoreExtensions) {
      this.registry.accept(property);
    }
    return property;
  }

  private PropertyMetadata<V> newMetadata() {

    Validator<? super V> validator = null;
    if (this.validatorBuilder != null) {
      validator = this.validatorBuilder.build();
    }
    AttributeReadOnly lock = null;
    if (this.parent != null) {
      lock = this.parent.getLock();
    }
    return PropertyMetadata.of(lock, validator, null, null, this.metadataMap);
  }

  /**
   * @param name the {@link Property#getName() property name} of the {@link Property} to build.
   * @param newMetadata the {@link PropertyMetadata}.
   * @return the {@link Property} to {@link #build(String) build}.
   */
  protected abstract P build(String name, PropertyMetadata<V> newMetadata);

  /**
   * @return a {@link ListPropertyBuilder} using {@link #build(String) this property configuration} for its elements.
   */
  public ListPropertyBuilder<V> asList() {

    return builder(new ListPropertyBuilder<>(this.parent, build("ListItem", true)));
  }

  /**
   * @return a {@link SetPropertyBuilder} using {@link #build(String) this property configuration} for its elements.
   */
  public SetPropertyBuilder<V> asSet() {

    return builder(new SetPropertyBuilder<>(this.parent, build("SetItem", true)));
  }

  /**
   * @return a {@link MapPropertyBuilder} using {@link #build(String) this property configuration} for its values and
   *         {@link String} as keys.
   */
  public MapPropertyBuilder<String, V> asMap() {

    return builder(new MapPropertyBuilder<>(this.parent, build("MapItem", true)));
  }

  /**
   * @param <T> type of the {@link PropertyBuilder}.
   * @param builder the {@link PropertyBuilder}.
   * @return the configured {@code builder}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected <T extends PropertyBuilder<?, ?, ?, ?>> T builder(T builder) {

    if (this.registry != null) {
      builder.registry((Consumer) this.registry);
    }
    if (this.factory != null) {
      builder.factory((Function) this.factory);
    }
    return builder;
  }

  /**
   * @param <K> type of the {@link Map#containsKey(Object) keys}.
   * @param keyProperty the {@link io.github.mmm.property.container.map.MapProperty#getKeyProperty() key property}.
   * @return a {@link MapPropertyBuilder} using {@link #build(String) this property configuration} for its values and
   *         {@link String} as keys.
   */
  public <K> MapPropertyBuilder<K, V> asMap(SimpleProperty<K> keyProperty) {

    return new MapPropertyBuilder<>(this.parent, keyProperty, build("Value"));
  }

}
