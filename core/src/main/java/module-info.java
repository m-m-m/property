@SuppressWarnings("all") //
module io.github.mmm.property {

  requires transitive org.slf4j;

  requires transitive io.github.mmm.marshall;

  requires static io.github.mmm.marshall.jsonp;

  requires transitive io.github.mmm.value.observable;

  requires transitive io.github.mmm.validation;

  uses io.github.mmm.property.factory.PropertyFactory;

  provides io.github.mmm.property.factory.PropertyFactory //
      with io.github.mmm.property.booleans.PropertyFactoryBoolean, //
      io.github.mmm.property.container.list.PropertyFactoryList, //
      io.github.mmm.property.container.map.PropertyFactoryMap, //
      io.github.mmm.property.container.set.PropertyFactorySet, //
      io.github.mmm.property.number.bigdecimal.PropertyFactoryBigDecimal, //
      io.github.mmm.property.number.biginteger.PropertyFactoryBigInteger, //
      io.github.mmm.property.number.bytes.PropertyFactoryByte, //
      io.github.mmm.property.number.doubles.PropertyFactoryDouble, //
      io.github.mmm.property.number.floats.PropertyFactoryFloat, //
      io.github.mmm.property.number.integers.PropertyFactoryInteger, //
      io.github.mmm.property.number.longs.PropertyFactoryLong, //
      io.github.mmm.property.number.shorts.PropertyFactoryShort, //
      io.github.mmm.property.object.PropertyFactoryObject, //
      io.github.mmm.property.string.PropertyFactoryString, //
      io.github.mmm.property.temporal.instant.PropertyFactoryInstant, //
      io.github.mmm.property.temporal.localdate.PropertyFactoryLocalDate, //
      io.github.mmm.property.temporal.localdatetime.PropertyFactoryLocalDateTime;

  exports io.github.mmm.property;

  exports io.github.mmm.property.booleans;

  // exports net.sf.mmm.property.api.comparables;

  exports io.github.mmm.property.container;

  exports io.github.mmm.property.container.list;

  exports io.github.mmm.property.container.map;

  exports io.github.mmm.property.container.set;

  exports io.github.mmm.property.object;

  exports io.github.mmm.property.string;

  exports io.github.mmm.property.factory;

  exports io.github.mmm.property.number;

  exports io.github.mmm.property.number.bigdecimal;

  exports io.github.mmm.property.number.biginteger;

  exports io.github.mmm.property.number.bytes;

  exports io.github.mmm.property.number.doubles;

  exports io.github.mmm.property.number.floats;

  exports io.github.mmm.property.number.integers;

  exports io.github.mmm.property.number.longs;

  exports io.github.mmm.property.number.shorts;

  exports io.github.mmm.property.temporal;

  exports io.github.mmm.property.temporal.instant;

  exports io.github.mmm.property.temporal.localdate;

  exports io.github.mmm.property.temporal.localdatetime;

}
