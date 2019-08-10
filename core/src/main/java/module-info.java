module net.sf.mmm.property.api {

  requires transitive org.slf4j;

  requires transitive net.sf.mmm.marshall;

  requires static net.sf.mmm.marshall.jsonp;

  requires transitive net.sf.mmm.value.observable;

  requires static javax.inject;

  requires java.annotation;

  requires mmm.util.lang;

  requires transitive mmm.util.validation;

  exports net.sf.mmm.property.api;

  exports net.sf.mmm.property.api.booleans;

  // exports net.sf.mmm.property.api.comparables;

  exports net.sf.mmm.property.api.objects;

  exports net.sf.mmm.property.api.strings;

  exports net.sf.mmm.property.api.numbers;

  exports net.sf.mmm.property.api.numbers.bigdecimals;

  exports net.sf.mmm.property.api.numbers.bigintegers;

  exports net.sf.mmm.property.api.numbers.bytes;

  exports net.sf.mmm.property.api.numbers.doubles;

  exports net.sf.mmm.property.api.numbers.floats;

  exports net.sf.mmm.property.api.numbers.integers;

  exports net.sf.mmm.property.api.numbers.longs;

  exports net.sf.mmm.property.api.numbers.shorts;

  exports net.sf.mmm.property.api.temporals;

  exports net.sf.mmm.property.api.temporals.instants;

  exports net.sf.mmm.property.api.temporals.localdates;

  exports net.sf.mmm.property.api.temporals.localdatetimes;

  // exports net.sf.mmm.property.api.containers;
  //
  // exports net.sf.mmm.property.api.containers.lists;
  //
  // exports net.sf.mmm.property.api.containers.maps;
  //
  // exports net.sf.mmm.property.api.containers.sets;

}
