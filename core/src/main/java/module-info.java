/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
/**
 * Provides advanced properties with support for change-listeners, bindings, validation, and marshalling.
 */
@SuppressWarnings("all") //
module io.github.mmm.property {

  requires transitive org.slf4j;

  requires transitive io.github.mmm.marshall;

  requires transitive io.github.mmm.scanner;

  requires static io.github.mmm.marshall.json;

  requires transitive io.github.mmm.value.converter;

  requires transitive io.github.mmm.value.observable;

  requires transitive io.github.mmm.validation;

  requires transitive io.github.mmm.base.metainfo;

  uses io.github.mmm.property.factory.PropertyFactory;

  provides io.github.mmm.property.factory.PropertyFactory //
      with io.github.mmm.property.booleans.PropertyFactoryBoolean, //
      io.github.mmm.property.container.list.PropertyFactoryList, //
      io.github.mmm.property.container.map.PropertyFactoryMap, //
      io.github.mmm.property.container.set.PropertyFactorySet, //
      io.github.mmm.property.enumeration.PropertyFactoryEnum, //
      io.github.mmm.property.locale.PropertyFactoryLocale, //
      io.github.mmm.property.number.bigdecimal.PropertyFactoryBigDecimal, //
      io.github.mmm.property.number.biginteger.PropertyFactoryBigInteger, //
      io.github.mmm.property.number.bytes.PropertyFactoryByte, //
      io.github.mmm.property.number.doubles.PropertyFactoryDouble, //
      io.github.mmm.property.number.floats.PropertyFactoryFloat, //
      io.github.mmm.property.number.integers.PropertyFactoryInteger, //
      io.github.mmm.property.number.longs.PropertyFactoryLong, //
      io.github.mmm.property.number.shorts.PropertyFactoryShort, //
      io.github.mmm.property.object.PropertyFactoryObject, //
      io.github.mmm.property.pattern.PropertyFactoryPattern, //
      io.github.mmm.property.range.PropertyFactoryRange, //
      io.github.mmm.property.string.PropertyFactoryPassword, //
      io.github.mmm.property.string.PropertyFactoryString, //
      io.github.mmm.property.time.dayofweek.PropertyFactoryDayOfWeek, //
      io.github.mmm.property.time.duration.PropertyFactoryDuration, //
      io.github.mmm.property.time.instant.PropertyFactoryInstant, //
      io.github.mmm.property.time.localdate.PropertyFactoryLocalDate, //
      io.github.mmm.property.time.localdatetime.PropertyFactoryLocalDateTime, //
      io.github.mmm.property.time.localtime.PropertyFactoryLocalTime, //
      io.github.mmm.property.time.month.PropertyFactoryMonth, //
      io.github.mmm.property.time.offsetdatetime.PropertyFactoryOffsetDateTime, //
      io.github.mmm.property.time.offsettime.PropertyFactoryOffsetTime, //
      io.github.mmm.property.time.year.PropertyFactoryYear, //
      io.github.mmm.property.time.zoneddatetime.PropertyFactoryZonedDateTime //
  ;

  exports io.github.mmm.property;

  exports io.github.mmm.property.booleans;

  exports io.github.mmm.property.container;

  exports io.github.mmm.property.container.collection;

  exports io.github.mmm.property.container.list;

  exports io.github.mmm.property.container.map;

  exports io.github.mmm.property.container.set;

  exports io.github.mmm.property.criteria;

  exports io.github.mmm.property.object;

  exports io.github.mmm.property.pattern;

  exports io.github.mmm.property.range;

  exports io.github.mmm.property.string;

  exports io.github.mmm.property.enumeration;

  exports io.github.mmm.property.factory;

  exports io.github.mmm.property.locale;

  exports io.github.mmm.property.number;

  exports io.github.mmm.property.number.bigdecimal;

  exports io.github.mmm.property.number.biginteger;

  exports io.github.mmm.property.number.bytes;

  exports io.github.mmm.property.number.doubles;

  exports io.github.mmm.property.number.floats;

  exports io.github.mmm.property.number.integers;

  exports io.github.mmm.property.number.longs;

  exports io.github.mmm.property.number.shorts;

  exports io.github.mmm.property.time;

  exports io.github.mmm.property.time.dayofweek;

  exports io.github.mmm.property.time.duration;

  exports io.github.mmm.property.time.instant;

  exports io.github.mmm.property.time.localdate;

  exports io.github.mmm.property.time.localdatetime;

  exports io.github.mmm.property.time.localtime;

  exports io.github.mmm.property.time.month;

  exports io.github.mmm.property.time.offsetdatetime;

  exports io.github.mmm.property.time.offsettime;

  exports io.github.mmm.property.time.year;

  exports io.github.mmm.property.time.zoneddatetime;
}
