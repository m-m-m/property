module net.sf.mmm.property {

  requires transitive org.slf4j;

  requires transitive net.sf.mmm.marshall;

  requires static net.sf.mmm.marshall.jsonp;

  requires transitive net.sf.mmm.value.observable;

  requires transitive net.sf.mmm.validation;

  uses net.sf.mmm.property.factory.PropertyFactory;

  provides net.sf.mmm.property.factory.PropertyFactory //
      with net.sf.mmm.property.booleans.PropertyFactoryBoolean, //
      net.sf.mmm.property.container.list.PropertyFactoryList, //
      net.sf.mmm.property.container.map.PropertyFactoryMap, //
      net.sf.mmm.property.container.set.PropertyFactorySet, //
      net.sf.mmm.property.number.bigdecimal.PropertyFactoryBigDecimal, //
      net.sf.mmm.property.number.biginteger.PropertyFactoryBigInteger, //
      net.sf.mmm.property.number.bytes.PropertyFactoryByte, //
      net.sf.mmm.property.number.doubles.PropertyFactoryDouble, //
      net.sf.mmm.property.number.floats.PropertyFactoryFloat, //
      net.sf.mmm.property.number.integers.PropertyFactoryInteger, //
      net.sf.mmm.property.number.longs.PropertyFactoryLong, //
      net.sf.mmm.property.number.shorts.PropertyFactoryShort, //
      net.sf.mmm.property.object.PropertyFactoryObject, //
      net.sf.mmm.property.string.PropertyFactoryString, //
      net.sf.mmm.property.temporal.instant.PropertyFactoryInstant, //
      net.sf.mmm.property.temporal.localdate.PropertyFactoryLocalDate, //
      net.sf.mmm.property.temporal.localdatetime.PropertyFactoryLocalDateTime;

  exports net.sf.mmm.property;

  exports net.sf.mmm.property.booleans;

  // exports net.sf.mmm.property.api.comparables;

  exports net.sf.mmm.property.container;

  exports net.sf.mmm.property.container.list;

  exports net.sf.mmm.property.container.map;

  exports net.sf.mmm.property.container.set;

  exports net.sf.mmm.property.object;

  exports net.sf.mmm.property.string;

  exports net.sf.mmm.property.factory;

  exports net.sf.mmm.property.number;

  exports net.sf.mmm.property.number.bigdecimal;

  exports net.sf.mmm.property.number.biginteger;

  exports net.sf.mmm.property.number.bytes;

  exports net.sf.mmm.property.number.doubles;

  exports net.sf.mmm.property.number.floats;

  exports net.sf.mmm.property.number.integers;

  exports net.sf.mmm.property.number.longs;

  exports net.sf.mmm.property.number.shorts;

  exports net.sf.mmm.property.temporal;

  exports net.sf.mmm.property.temporal.instant;

  exports net.sf.mmm.property.temporal.localdate;

  exports net.sf.mmm.property.temporal.localdatetime;

}
