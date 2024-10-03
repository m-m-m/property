/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.builder;

import static io.github.mmm.property.builder.PropertyBuildersHelper.accept;
import static io.github.mmm.property.builder.PropertyBuildersHelper.builder;
import static io.github.mmm.property.builder.PropertyBuildersHelper.get;

import java.util.function.Consumer;
import java.util.function.Function;

import io.github.mmm.property.AttributeReadOnly;
import io.github.mmm.property.Property;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.booleans.BooleanProperty;
import io.github.mmm.property.builder.lang.BooleanPropertyBuilder;
import io.github.mmm.property.builder.lang.PatternPropertyBuilder;
import io.github.mmm.property.builder.lang.StringPropertyBuilder;
import io.github.mmm.property.builder.number.BigDecimalPropertyBuilder;
import io.github.mmm.property.builder.number.BigIntegerPropertyBuilder;
import io.github.mmm.property.builder.number.BytePropertyBuilder;
import io.github.mmm.property.builder.number.DoublePropertyBuilder;
import io.github.mmm.property.builder.number.FloatPropertyBuilder;
import io.github.mmm.property.builder.number.IntegerPropertyBuilder;
import io.github.mmm.property.builder.number.LongPropertyBuilder;
import io.github.mmm.property.builder.number.ShortPropertyBuilder;
import io.github.mmm.property.builder.time.DayOfWeekPropertyBuilder;
import io.github.mmm.property.builder.time.InstantPropertyBuilder;
import io.github.mmm.property.builder.time.LocalDatePropertyBuilder;
import io.github.mmm.property.builder.time.LocalDateTimePropertyBuilder;
import io.github.mmm.property.builder.time.LocalTimePropertyBuilder;
import io.github.mmm.property.builder.time.MonthPropertyBuilder;
import io.github.mmm.property.builder.time.OffsetDateTimePropertyBuilder;
import io.github.mmm.property.builder.time.OffsetTimePropertyBuilder;
import io.github.mmm.property.builder.time.YearPropertyBuilder;
import io.github.mmm.property.builder.time.ZonedDateTimePropertyBuilder;
import io.github.mmm.property.number.bigdecimal.BigDecimalProperty;
import io.github.mmm.property.number.biginteger.BigIntegerProperty;
import io.github.mmm.property.number.bytes.ByteProperty;
import io.github.mmm.property.number.doubles.DoubleProperty;
import io.github.mmm.property.number.floats.FloatProperty;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.property.number.longs.LongProperty;
import io.github.mmm.property.number.shorts.ShortProperty;
import io.github.mmm.property.pattern.PatternProperty;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.property.time.dayofweek.DayOfWeekProperty;
import io.github.mmm.property.time.instant.InstantProperty;
import io.github.mmm.property.time.localdate.LocalDateProperty;
import io.github.mmm.property.time.localdatetime.LocalDateTimeProperty;
import io.github.mmm.property.time.localtime.LocalTimeProperty;
import io.github.mmm.property.time.month.MonthProperty;
import io.github.mmm.property.time.offsetdatetime.OffsetDateTimeProperty;
import io.github.mmm.property.time.offsettime.OffsetTimeProperty;
import io.github.mmm.property.time.year.YearProperty;
import io.github.mmm.property.time.zoneddatetime.ZonedDateTimeProperty;

/**
 * Interface for a factory of {@link PropertyBuilder}s and {@link Property properties}. May optionally implement one of
 * the following features:
 * <ul>
 * <li>{@link Consumer}{@literal <[Writable]Property<?>>} to receive any property that has been created.</li>
 * <li>{@link Function}{@literal <String, [Writable]Property<?>>} to provide tweaked or already existing
 * properties.</li>
 * </ul>
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public interface PropertyBuilders {

  /**
   * @return the {@link PropertyMetadata#getLock() lock}.
   */
  AttributeReadOnly getLock();

  /**
   * @return a new {@link StringPropertyBuilder}.
   */
  default StringPropertyBuilder newString() {

    return builder(new StringPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link StringProperty}.
   */
  default StringProperty newString(String name) {

    return get(name, this, metadata -> accept(new StringProperty(name, metadata), this));
  }

  /**
   * @return a new {@link StringPropertyBuilder}.
   */
  default PatternPropertyBuilder newPattern() {

    return builder(new PatternPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link PatternProperty}.
   */
  default PatternProperty newPattern(String name) {

    return get(name, this, metadata -> accept(new PatternProperty(name, metadata), this));
  }

  /**
   * @return a new {@link BooleanPropertyBuilder}.
   */
  default BooleanPropertyBuilder newBoolean() {

    return builder(new BooleanPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link BooleanProperty}.
   */
  default BooleanProperty newBoolean(String name) {

    return get(name, this, metadata -> accept(new BooleanProperty(name, metadata), this));
  }

  /**
   * @return a new {@link LongPropertyBuilder}.
   */
  default LongPropertyBuilder newLong() {

    return builder(new LongPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link LongProperty}.
   */
  default LongProperty newLong(String name) {

    return get(name, this, metadata -> accept(new LongProperty(name, metadata), this));
  }

  /**
   * @return a new {@link BigDecimalPropertyBuilder}.
   */
  default BigDecimalPropertyBuilder newBigDecimal() {

    return builder(new BigDecimalPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link BigDecimalProperty}.
   */
  default BigDecimalProperty newBigDecimal(String name) {

    return get(name, this, metadata -> accept(new BigDecimalProperty(name, metadata), this));
  }

  /**
   * @return a new {@link BigIntegerPropertyBuilder}.
   */
  default BigIntegerPropertyBuilder newBigInteger() {

    return builder(new BigIntegerPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link BigIntegerProperty}.
   */
  default BigIntegerProperty newBigInteger(String name) {

    return get(name, this, metadata -> accept(new BigIntegerProperty(name, metadata), this));
  }

  /**
   * @return a new {@link IntegerPropertyBuilder}.
   */
  default IntegerPropertyBuilder newInteger() {

    return builder(new IntegerPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link IntegerProperty}.
   */
  default IntegerProperty newInteger(String name) {

    return get(name, this, metadata -> accept(new IntegerProperty(name, metadata), this));
  }

  /**
   * @return a new {@link DoublePropertyBuilder}.
   */
  default DoublePropertyBuilder newDouble() {

    return builder(new DoublePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link DoubleProperty}.
   */
  default DoubleProperty newDouble(String name) {

    return get(name, this, metadata -> accept(new DoubleProperty(name, metadata), this));
  }

  /**
   * @return a new {@link FloatPropertyBuilder}.
   */
  default FloatPropertyBuilder newFloat() {

    return builder(new FloatPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link FloatProperty}.
   */
  default FloatProperty newFloat(String name) {

    return get(name, this, metadata -> accept(new FloatProperty(name, metadata), this));
  }

  /**
   * @return a new {@link ShortPropertyBuilder}.
   */
  default ShortPropertyBuilder newShort() {

    return builder(new ShortPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link ShortProperty}.
   */
  default ShortProperty newShort(String name) {

    return get(name, this, metadata -> accept(new ShortProperty(name, metadata), this));
  }

  /**
   * @return a new {@link BytePropertyBuilder}.
   */
  default BytePropertyBuilder newByte() {

    return builder(new BytePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link ByteProperty}.
   */
  default ByteProperty newByte(String name) {

    return get(name, this, metadata -> accept(new ByteProperty(name, metadata), this));
  }

  /**
   * @return a new {@link DayOfWeekPropertyBuilder}.
   */
  default DayOfWeekPropertyBuilder newDayOfWeek() {

    return builder(new DayOfWeekPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link DayOfWeekProperty}.
   */
  default DayOfWeekProperty newDayOfWeek(String name) {

    return get(name, this, metadata -> accept(new DayOfWeekProperty(name, metadata), this));
  }

  /**
   * @return a new {@link InstantPropertyBuilder}.
   */
  default InstantPropertyBuilder newInstant() {

    return builder(new InstantPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link InstantProperty}.
   */
  default InstantProperty newInstant(String name) {

    return get(name, this, metadata -> accept(new InstantProperty(name, metadata), this));
  }

  /**
   * @return a new {@link LocalDateTimePropertyBuilder}.
   */
  default LocalDateTimePropertyBuilder newLocalDateTime() {

    return builder(new LocalDateTimePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link LocalDateTimeProperty}.
   */
  default LocalDateTimeProperty newLocalDateTime(String name) {

    return get(name, this, metadata -> accept(new LocalDateTimeProperty(name, metadata), this));
  }

  /**
   * @return a new {@link LocalDatePropertyBuilder}.
   */
  default LocalDatePropertyBuilder newLocalDate() {

    return builder(new LocalDatePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link LocalDateProperty}.
   */
  default LocalDateProperty newLocalDate(String name) {

    return get(name, this, metadata -> accept(new LocalDateProperty(name, metadata), this));
  }

  /**
   * @return a new {@link LocalTimePropertyBuilder}.
   */
  default LocalTimePropertyBuilder newLocalTime() {

    return builder(new LocalTimePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link LocalTimeProperty}.
   */
  default LocalTimeProperty newLocalTime(String name) {

    return get(name, this, metadata -> accept(new LocalTimeProperty(name, metadata), this));
  }

  /**
   * @return a new {@link MonthPropertyBuilder}.
   */
  default MonthPropertyBuilder newMonth() {

    return builder(new MonthPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link MonthProperty}.
   */
  default MonthProperty newMonth(String name) {

    return get(name, this, metadata -> accept(new MonthProperty(name, metadata), this));
  }

  /**
   * @return a new {@link OffsetDateTimePropertyBuilder}.
   */
  default OffsetDateTimePropertyBuilder newOffsetDateTime() {

    return builder(new OffsetDateTimePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link OffsetDateTimeProperty}.
   */
  default OffsetDateTimeProperty newOffsetDateTime(String name) {

    return get(name, this, metadata -> accept(new OffsetDateTimeProperty(name, metadata), this));
  }

  /**
   * @return a new {@link OffsetTimePropertyBuilder}.
   */
  default OffsetTimePropertyBuilder newOffsetTime() {

    return builder(new OffsetTimePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link OffsetTimeProperty}.
   */
  default OffsetTimeProperty newOffsetTime(String name) {

    return get(name, this, metadata -> accept(new OffsetTimeProperty(name, metadata), this));
  }

  /**
   * @return a new {@link YearPropertyBuilder}.
   */
  default YearPropertyBuilder newYear() {

    return builder(new YearPropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link YearProperty}.
   */
  default YearProperty newYear(String name) {

    return get(name, this, metadata -> accept(new YearProperty(name, metadata), this));
  }

  /**
   * @return a new {@link ZonedDateTimePropertyBuilder}.
   */
  default ZonedDateTimePropertyBuilder newZonedDateTime() {

    return builder(new ZonedDateTimePropertyBuilder(getLock()), this);
  }

  /**
   * @param name the {@link Property#getName() property name}.
   * @return a new {@link ZonedDateTimeProperty}.
   */
  default ZonedDateTimeProperty newZonedDateTime(String name) {

    return get(name, this, metadata -> accept(new ZonedDateTimeProperty(name, metadata), this));
  }

}
