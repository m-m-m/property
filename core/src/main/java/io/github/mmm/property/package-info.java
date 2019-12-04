/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
/**
 * Provides the API for generic and powerful properties. <a name="documentation"></a>
 * <h2>Property API</h2><br>
 * Unfortunately Java has no build in properties and will never have. For decades people had to waste their time writing
 * stupid Java beans with getters, setters and other boilerplate code. Writing generic code on top of this is extremely
 * complex and error prone and also results in relatively poor performance, access modifier violations, and many other
 * flaws. Hence, data-bindings in Java have been a nightmare and where never compiler safe.<br>
 * JavaFx wanted to cure this pain a little by introducing {@code javafx.beans.property.Property} a real property. This
 * already brings a lot of benefits especially for data-bindings, but writing Java beans manually based on such
 * properties is causing even more boiler-plate code. Further {@code javafx.beans.property.Property} still lacks a lot
 * of build-in features such as support to determine the value type, validation, read-only views, JSON mapping, etc.<br>
 * This API (and its implementation) comes with {@link io.github.mmm.property.ReadableProperty} and
 * {@link io.github.mmm.property.WritableProperty} as well as all the sub-interfaces and implementations to give you
 * full support for all your needs. Further, it the module {@code io.github.mmm.bean} from {@code mmm-bean} adds
 * {@code io.github.mmm.bean.Bean} support on top of this properties and saves you from all the boilerplate code to
 * implement your beans. Stop wasting your time and use your time to actually implement real value.<br>
 * Note: You can also implement custom datatypes as property just like
 * {@link io.github.mmm.property.string.StringListProperty} or
 * {@link io.github.mmm.property.temporal.DurationInSecondsProperty}.
 *
 * @see io.github.mmm.property.string.StringProperty
 * @see io.github.mmm.property.booleans.BooleanProperty
 * @see io.github.mmm.property.number.integers.IntegerProperty
 * @see io.github.mmm.property.number.longs.LongProperty
 * @see io.github.mmm.property.number.bigdecimal.BigDecimalProperty
 * @see io.github.mmm.property.temporal.instant.InstantProperty
 * @see io.github.mmm.property.temporal.localdate.LocalDateProperty
 * @see io.github.mmm.property.temporal.localdatetime.LocalDateTimeProperty
 * @see io.github.mmm.property.container.list.ListProperty
 * @see io.github.mmm.property.container.set.SetProperty
 * @see io.github.mmm.property.container.map.MapProperty
 */
package io.github.mmm.property;
